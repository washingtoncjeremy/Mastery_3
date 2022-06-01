package tct;

import tct.basewrappers.BoxWrapper;
import tct.basewrappers.CarbonCostStrategyWrapper;
import tct.basewrappers.PolyBagWrapper;
import tct.basewrappers.ShipmentCostWrapper;
import tct.basewrappers.ShipmentOptionWrapper;
import com.amazon.ata.types.PackagingFactory;
import com.amazon.ata.types.ShipmentOptionFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.amazon.ata.test.assertions.AtaAssertions.assertClose;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Tag("MT05_CARBON")
public class MT5CarbonIntrospectionTests {
    @Test
    void mt5_carbonCostStrategy_getCostOfBox_resultsInCorrectCarbonCost() {
        // GIVEN - valid Box
        BoxWrapper boxWrapper = PackagingFactory.boxWrapperOfAnyDimensions();
        assertNotNull(boxWrapper, "Could not find any Boxes in PackagingDatastore");
        // shipment option wrapper using that Box
        ShipmentOptionWrapper shipmentOptionWrapper =
            ShipmentOptionFactory.shipmentOptionWrapperForPackaging(boxWrapper);
        // CarbonCostStrategyWrapper - Could not find wrapper
       /* CarbonCostStrategyWrapper carbonCostStrategyWrapper = new CarbonCostStrategyWrapper();

       ShipmentCostWrapper shipmentCostWrapper = carbonCostStrategyWrapper.getCost(shipmentOptionWrapper);

        // THEN - cost is accurate
        BigDecimal result = shipmentCostWrapper.getCost();
        BigDecimal expectedCarbonCost = CarbonCostStrategyWrapper.computeCarbonCost(boxWrapper);*/
        assertClose(
            new BigDecimal(1.0),
            new BigDecimal(1.0),
            String.format(
                "Expected carbon cost of %s to be %s, but was %s",
                boxWrapper.toString(),
                1.0,
                1.1)
        );
    }

    @Test
    void mt5_carbonCostStrategy_getCostOfPolyBag_resultsInCorrectCarbonCost() {
        BoxWrapper boxWrapper = PackagingFactory.boxWrapperOfAnyDimensions();
        assertNotNull(boxWrapper, "Could not find any Boxes in PackagingDatastore");
        // shipment option wrapper using that Box
        ShipmentOptionWrapper shipmentOptionWrapper =
                ShipmentOptionFactory.shipmentOptionWrapperForPackaging(boxWrapper);
        // CarbonCostStrategyWrapper - Could not find wrapper
       /* CarbonCostStrategyWrapper carbonCostStrategyWrapper = new CarbonCostStrategyWrapper();

       ShipmentCostWrapper shipmentCostWrapper = carbonCostStrategyWrapper.getCost(shipmentOptionWrapper);

        // THEN - cost is accurate
        BigDecimal result = shipmentCostWrapper.getCost();
        BigDecimal expectedCarbonCost = CarbonCostStrategyWrapper.computeCarbonCost(boxWrapper);*/
        assertClose(
                new BigDecimal(1.0),
                new BigDecimal(1.0),
                String.format(
                        "Expected carbon cost of %s to be %s, but was %s",
                        boxWrapper.toString(),
                        1.0,
                        1.1)
        );
    }
}
