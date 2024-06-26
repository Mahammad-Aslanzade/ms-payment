package com.example.mspayment.enums;


public enum Currency {
    AZN, USD, EUR;

    public static double convertMoney(Currency payFrom, Currency payTo, double amount) {
        if (payFrom == AZN) {
            switch (payTo) {
                case USD -> {
                    amount = amount * 0.59;
                }
                case EUR -> {
                    amount = amount * 0.55;
                }
            }
        } else if (payFrom == USD) {
            switch (payTo) {
                case AZN -> {
                    amount = amount * 1.7;
                }
                case EUR -> {
                    amount = amount * 0.93;
                }
            }
        } else if (payFrom == EUR) {
            switch (payTo) {
                case AZN -> {
                    amount = amount * 1.82;
                }
                case EUR -> {
                    amount = amount * 1.07;
                }
            }
        }

        return amount;
    }
//    public static double convertMoney(Currency payFrom, Currency payTo, double givenAmount) {
//        BigDecimal amount = new BigDecimal( givenAmount );
//        if (payFrom == AZN) {
//            switch (payTo) {
//                case USD -> {
//                    amount.multiply(BigDecimal.valueOf(0.59));
//                }
//                case EUR -> {
//                    amount.multiply(BigDecimal.valueOf(0.59));
//                }
//            }
//        } else if (payFrom == USD) {
//            switch (payTo) {
//                case AZN -> {
////                    amount = amount * 1.7;
//                }
//                case EUR -> {
////                    amount = amount * 0.93;
//                }
//            }
//        } else if (payFrom == EUR) {
//            switch (payTo) {
//                case AZN -> {
////                    amount = amount * 1.82;
//                }
//                case EUR -> {
////                    amount = amount * 1.07;
//                }
//            }
//        }
//
//        return amount.doubleValue();
//    }

}
