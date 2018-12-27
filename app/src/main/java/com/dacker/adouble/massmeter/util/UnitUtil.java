package com.dacker.adouble.massmeter.util;

import com.dacker.adouble.massmeter.core.unitof.UnitOf;

public class UnitUtil {

    public static final String [] lengthUnits = {"mm", "cm", "dm", "m", "km"};
    public static final String [] volumeUnits = {"mm3", "cm3", "dm3", "m3", "km3"};
    public static final String [] massUnits = {"mg", "g", "kg", "cent", "ton"};
    public static final int defaultLenUnit =  3;
    public static final int defaultVolumeUnit =  3;
    public static final int defaultMassUnit =  2;

    public static class StringIntPair {
        public String first;
        public Integer second;

        public StringIntPair(String first, Integer second) {
            this.first = first;
            this.second = second;
        }
    }

    public static UnitOf.Length getLength(StringIntPair pair) {
        try {
            double val = Double.valueOf(pair.first);
            switch (lengthUnits[pair.second]) {
                case "mm":
                    return new UnitOf.Length().fromMillimeters(val);
                case "cm":
                    return new UnitOf.Length().fromCentimeters(val);
                case "dm":
                    return new UnitOf.Length().fromDecimeters(val);
                case "m":
                    return new UnitOf.Length().fromMeters(val);
                case "km":
                    return new UnitOf.Length().fromKilometers(val);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    public static UnitOf.Volume getVolume(StringIntPair pair) {
        try {
            double val = Double.valueOf(pair.first);
            switch (volumeUnits[pair.second]) {
                case "mm3":
                    return new UnitOf.Volume().fromCubicMillimeters(val);
                case "cm3":
                    return new UnitOf.Volume().fromCubicCentimeters(val);
                case "dm3":
                    return new UnitOf.Volume().fromCubicDecimeters(val);
                case "m3":
                    return new UnitOf.Volume().fromCubicMeters(val);
                case "km3":
                    return new UnitOf.Volume().fromCubicKilometers(val);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    public static UnitOf.Mass getMass(StringIntPair pair) {
        try {
            double val = Double.valueOf(pair.first);
            switch (massUnits[pair.second]) {
                case "mg":
                    return new UnitOf.Mass().fromMilligrams(val);
                case "g":
                    return new UnitOf.Mass().fromGrams(val);
                case "kg":
                    return new UnitOf.Mass().fromKilograms(val);
                case "cent":
                    return new UnitOf.Mass().fromCentners(val);
                case "ton":
                    return new UnitOf.Mass().fromTonsMetric(val);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    public static Double convertVolumeTo(UnitOf.Volume unit, String toUnit) {
        switch (toUnit) {
            case "mm3":
                return unit.toCubicMillimeters();
            case "cm3":
                return unit.toCubicCentimeters();
            case "dm3":
                return unit.toCubicDecimeters();
            case "m3":
                return unit.toCubicMeters();
            case "km3":
                return unit.toCubicKilometers();
        }
        return null;
    }

    public static Double convertMassTo(UnitOf.Mass unit, String toUnit) {
        switch (toUnit) {
            case "mg":
                return unit.toMilligrams();
            case "g":
                return unit.toGrams();
            case "kg":
                return unit.toKilograms();
            case "cent":
                return unit.toCentners();
            case "ton":
                return unit.toTonsMetric();
        }
        return null;
    }

}
