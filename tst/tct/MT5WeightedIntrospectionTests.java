package tct;

import com.amazon.ata.test.helper.AtaTestHelper;
import com.amazon.ata.test.reflect.MethodInvoker;
import com.amazon.ata.test.reflect.MethodQuery;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tct.basewrappers.BoxWrapper;
import tct.basewrappers.CarbonCostStrategyWrapper;
import tct.basewrappers.MonetaryCostStrategyWrapper;
import tct.basewrappers.PolyBagWrapper;
import tct.basewrappers.ShipmentCostWrapper;
import tct.basewrappers.ShipmentOptionWrapper;
import tct.basewrappers.WeightedCostStrategyWrapper;
import com.amazon.ata.types.PackagingFactory;
import com.amazon.ata.types.ShipmentOptionFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import static com.amazon.ata.test.assertions.AtaAssertions.assertClose;
import static com.amazon.ata.test.assertions.PlantUmlClassDiagramAssertions.*;
import static com.amazon.ata.test.assertions.PlantUmlClassDiagramAssertions.assertClassDiagramIncludesImplementsRelationship;
import static com.amazon.ata.test.helper.PlantUmlClassDiagramHelper.classDiagramIncludesContainsRelationship;
import static com.amazon.ata.test.helper.PlantUmlClassDiagramHelper.classDiagramIncludesRelationship;
import static org.junit.jupiter.api.Assertions.*;

@Tag("MT05_WEIGHTED")
public class MT5WeightedIntrospectionTests {
    private static final String CLASS_DIAGRAM_PATH = "mastery_task_05_CD.puml";

    @ParameterizedTest
    @ValueSource(strings = {"CostStrategy", "MonetaryCostStrategy", "CarbonCostStrategy", "WeightedCostStrategy"})
    void mt5_design_getClassDiagram_containsNewStrategyTypes(String strategyType) {
        // GIVEN - diagram path, expected type name
        // WHEN
        String content = AtaTestHelper.getFileContentFromResources(CLASS_DIAGRAM_PATH);

        // THEN - diagram includes expected strategy class
        assertClassDiagramContains(content, strategyType);
    }

    @Test
    void mt5_design_getClassDiagram_containsCostStrategyAsInterface() {
        // GIVEN - diagram path, expected interface name
        String interfaceName = "CostStrategy";

        // WHEN
        String content = AtaTestHelper.getFileContentFromResources(CLASS_DIAGRAM_PATH);

        // THEN - CostStrategy is an interface
        assertClassDiagramContainsInterface(content, interfaceName);
    }



    @Test
    void mt5_design_getClassDiagram_costStrategyHasGetCostMethod() {
        // GIVEN - diagram path
        // expected type
        String interfaceName = "CostStrategy";
        // expected method
        String methodName = "getCost";
        // expected arg type
        List<String> argTypes = ImmutableList.of("ShipmentOption");
        // expected return type
        String returnType = "ShipmentCost";

        // WHEN
        String content = AtaTestHelper.getFileContentFromResources(CLASS_DIAGRAM_PATH);

        // THEN - CostStrategy has expected method
        assertClassDiagramTypeContainsMethod(content, interfaceName, methodName, returnType, argTypes);
    }

    @ParameterizedTest
    @ValueSource(strings = {"MonetaryCostStrategy", "CarbonCostStrategy", "WeightedCostStrategy"})
    void mt5_design_getClassDiagram_costStrategyHasConcreteImplementations(String costStrategySubclass) {
        // GIVEN - diagram path, name of class implementing CostStrategy
        // WHEN
        String content = AtaTestHelper.getFileContentFromResources(CLASS_DIAGRAM_PATH);

        // THEN - found class implementing CostStrategy
        assertClassDiagramIncludesImplementsRelationship(content, costStrategySubclass, "CostStrategy");
    }

    @Test
    void mt5_design_getClassDiagram_weightedCostContainsCostImplementations() {
        // GIVEN - diagram path, name of class WeightedCostStrategy uses
        // WHEN
        String content = AtaTestHelper.getFileContentFromResources(CLASS_DIAGRAM_PATH);

        boolean hasWeightedContainsRelationship =
                classDiagramIncludesContainsRelationship(content, "WeightedCostStrategy", "CostStrategy") ||
                        (classDiagramIncludesRelationship(content, "MonetaryCostStrategy", "WeightedCostStrategy") &&
                                classDiagramIncludesRelationship(content, "CarbonCostStrategy", "WeightedCostStrategy")
                        );

        // THEN - found class relationship
        assertTrue(hasWeightedContainsRelationship,
                "Expected WeightedCostStrategy to have a contains relationship with other strategy classes.");
    }
}
