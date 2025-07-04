package services;

import contracts.Shippable;
import entities.Cart;
import utils.MathHelpers;
import utils.StringBuilderHelpers;

import java.util.List;

public class ShippingService {
    private final double SHIPMENT_COST_PER_KG = 5.0;
    private final StringBuilder shippingNoticeBuilder = new StringBuilder();

    public double handleShipment(List<Shippable> shippableItems)
    {
        double totalShipmentPrice = 0;
        double totalWeight = 0;
        StringBuilderHelpers.appendLine(shippingNoticeBuilder,"** Shipment notice **");

        for(var item : shippableItems)
        {
            var itemWeight = item.getWeight();
            totalShipmentPrice += getItemShippingPrice(itemWeight);
            totalWeight += itemWeight;
            StringBuilderHelpers.appendLine(shippingNoticeBuilder,item.getName() + '\t' + this.formatWeight(itemWeight));
        }
        StringBuilderHelpers.appendLine(shippingNoticeBuilder,String.format("Total package weight %s", formatWeight(totalWeight)));

        return totalShipmentPrice;
    }


    public void printShipmentNotice()
    {
        System.out.println(shippingNoticeBuilder.toString());
        shippingNoticeBuilder.setLength(0);
    }

    private double getItemShippingPrice(double weight)
    {
        return (weight/ 1000) * SHIPMENT_COST_PER_KG;
    }

    private String formatWeight(double weight){
        if(weight < 1000)
            return String.format("%sg", MathHelpers.round(weight,1));
        double weightInKg = weight/1000;
        return String.format("%sKg", MathHelpers.round(weightInKg,1));
    }
}
