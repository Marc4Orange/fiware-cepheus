package com.orange.cepheus.geo;

import com.espertech.esper.client.hook.AggregationFunctionFactory;
import com.espertech.esper.epl.agg.aggregator.AggregationMethod;
import com.espertech.esper.epl.agg.service.AggregationValidationContext;
import com.vividsolutions.jts.geom.Geometry;

import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.LinkedList;

//configuration.addPlugInAggregationFunctionFactory("geo:maxDistance", MaxDistance.class.getName());

/**
 * Created by marc on 02/11/2015.
 */
public class MaxDistance implements AggregationFunctionFactory {
    @Override
    public void setFunctionName(String functionName) {

    }

    @Override
    public void validate(AggregationValidationContext validationContext) {
        if (validationContext.getParameterTypes().length != 1 && validationContext.getParameterTypes()[0] != Geometry.class) {
            throw new IllegalArgumentException("MaxDistance requires a single parameter of type GeoPoint");
        }
    }

    @Override
    public Class getValueType() {
        return Double.class;
    }

    @Override
    public AggregationMethod newAggregator() {
        return new AM();
    }

    public class AM implements AggregationMethod {

        HashSet<Geometry> list = new HashSet<>();

        double maxLat, maxLong;

        @Override
        public void enter(Object value) {
            list.add((Geometry)value);
        }

        @Override
        public void leave(Object value) {
            list.remove((Geometry)value);
        }

        @Override
        public Object getValue() {
            return null;
        }

        @Override
        public Class getValueType() {
            return Double.class;
        }

        @Override
        public void clear() {
            list.clear();
        }
    }
}
