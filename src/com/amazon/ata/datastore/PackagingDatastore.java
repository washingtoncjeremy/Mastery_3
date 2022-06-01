package com.amazon.ata.datastore;

import com.amazon.ata.types.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Stores all configured packaging pairs for all fulfillment centers.
 */
public class PackagingDatastore {

    private Map<FulfillmentCenter, HashSet<FcPackagingOption>> fcPackagingOptionsMap = new HashMap<>();


    /**
     * The stored pairs of fulfillment centers to the packaging options they support.
     */
    private final List<FcPackagingOption> fcPackagingOptions = Arrays.asList(
            createFcPackagingOption("IND1", Material.CORRUGATE, "10", "10", "10"),
            createFcPackagingOption("ABE2", Material.CORRUGATE, "20", "20", "20"),
            createFcPackagingOption("ABE2", Material.CORRUGATE, "40", "40", "40"),
            createFcPackagingOption("YOW4", Material.CORRUGATE, "10", "10", "10"),
            createFcPackagingOption("YOW4", Material.CORRUGATE, "20", "20", "20"),
            createFcPackagingOption("YOW4", Material.CORRUGATE, "60", "60", "60"),
            createFcPackagingOption("IAD2", Material.CORRUGATE, "20", "20", "20"),
            createFcPackagingOption("IAD2", Material.LAMINATED_PLASTIC, "20", "20", "20"),
            createFcPackagingOption("PDX1", Material.CORRUGATE, "40", "40", "40"),
            createFcPackagingOption("PDX1", Material.CORRUGATE, "60", "60", "60"),
            createFcPackagingOption("PDX1", Material.CORRUGATE, "60", "60", "60")
    );

    public PackagingDatastore() {

        createFcPackagingOptionMap();

    }

    /**
     * Create fulfillment center packaging option from provided parameters.
     */
    /*private FcPackagingOption createFcPackagingOption(String fcCode, Material material,
                                                      String length, String width, String height) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Box packaging = new Box(material, new BigDecimal(length), new BigDecimal(width),
                new BigDecimal(height));

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }*/

    /**
     * Create fulfillment center packaging option from provided parameters.
     */
    private FcPackagingOption createFcPackagingOption(String fcCode, Material material,
                                                      String length, String width, String height) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Box packaging = new Box(material, new BigDecimal(length), new BigDecimal(width),
                new BigDecimal(height));

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    private void createFcPackagingOptionMap() {

        for(FcPackagingOption packagingOption : fcPackagingOptions) {

            if(!fcPackagingOptionsMap.containsKey(packagingOption.getFulfillmentCenter())) {

                fcPackagingOptionsMap.put(packagingOption.getFulfillmentCenter(), new HashSet<>(Arrays.asList(packagingOption)));

            }

        }

    }

    public List<FcPackagingOption> getFcPackagingOptions() {
        return fcPackagingOptions;
    }

    public Map<FulfillmentCenter, HashSet<FcPackagingOption>> getFcPackagingOptionsMap() {

        return fcPackagingOptionsMap;

    }

}
