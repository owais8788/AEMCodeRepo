package com.techcombank.core.models;

import lombok.Getter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
/**
 * This Sling model for debt panel
 **/
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DebtPanel {

    /**
     * The debt panel deposit amount label
     */
    @ValueMapValue
    @Getter
    private String depositAmountLabel;

    /**
     * The debt panel deposit amount tool
     */
    @ValueMapValue
    @Getter
    private String depositAmountTool;

    /**
     * The debt panel deposit amount input
     */
    @ValueMapValue
    @Getter
    private String depositAmountInput;

    /**
     * The debt panel intrest rate
     */
    @ValueMapValue
    @Getter
    private String interestRate;

    /**
     * The debt panel intrest rate tool
     */
    @ValueMapValue
    @Getter
    private String interestRateTool;

    /**
     * The debt panel intrest rate slider
     */
    @ValueMapValue
    @Getter
    private String interestRateSlider;

    /**
     * The debt panel term label text
     */
    @ValueMapValue
    @Getter
    private String termLabelText;

    /**
     * The debt panel term tool tip
     */
    @ValueMapValue
    @Getter
    private String termToolTip;

    /**
     * The debt panel term input field
     */
    @ValueMapValue
    @Getter
    private String termInputField;

    /**
     * The debt panel tool tip icon
     */
    @ValueMapValue
    @Getter
    private String toolTipIcon;

    /**
     * The debt panel currency text
     */
    @ValueMapValue
    @Getter
    private String currencyText;

    /**
     * The debt panel month text for term
     */
    @ValueMapValue
    @Getter
    private String monthText;

    /**
     * The debt panel output panel background image
     */
    @ValueMapValue
    @Getter
    private String outputPanelBg;

    /**
     * The debt panel icon image
     */
    @ValueMapValue
    @Getter
    private String iconImage;

    /**
     * The debt panel icon image alt text
     */
    @ValueMapValue
    @Getter
    private String iconImageAlt;

    /**
     * The debt panel interest paid label text
     */
    @ValueMapValue
    @Getter
    private String interestPaidLabel;

    /**
     * The debt panel interest paid output amount
     */
    @ValueMapValue
    @Getter
    private String interestPaidOutputAmount;

    /**
     * The debt panel total recieved label
     */
    @ValueMapValue
    @Getter
    private String totalRecievedLabel;

    /**
     * The debt panel total recieved output amount
     */
    @ValueMapValue
    @Getter
    private String totalRecievedOutputAmount;
}
