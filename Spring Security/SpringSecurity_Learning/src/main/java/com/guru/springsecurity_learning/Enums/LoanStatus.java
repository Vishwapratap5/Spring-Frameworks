package com.guru.springsecurity_learning.Enums;

public enum LoanStatus {

    PENDING,        // Loan approved but not yet disbursed / EMI not started

    ACTIVE,         // EMI schedule active, repayments ongoing

    CLOSED,         // Fully repaid, outstanding = 0

    DEFAULTED,      // Missed EMIs beyond allowed threshold

    CANCELLED       // Approved but user/bank cancelled before activation
}
