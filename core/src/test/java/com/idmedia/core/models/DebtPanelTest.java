package com.techcombank.core.models;

import com.techcombank.core.constants.TestConstants;
import com.techcombank.core.testcontext.AppAemContext;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
public class DebtPanelTest {

    private final AemContext aemContext = AppAemContext.newAemContext();
    private DebtPanel debtPanel;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(DebtPanel.class);
        aemContext.load().json(TestConstants.DEBTPANEL, "/debtPanel");
        aemContext.currentResource(TestConstants.DEBTPANELCOMP);
        Resource resource = aemContext.request().getResource();
        debtPanel = resource.adaptTo(DebtPanel.class);
    }
    @Test
    void depositAmountTest() {
        final String expected = "deposit amount";
        assertEquals(expected, debtPanel.getDepositAmountLabel());
    }
    @Test
    void depositAmountToolTest() {
        final String expected = "deposit tool tip";
        assertEquals(expected, debtPanel.getDepositAmountTool());
    }

    @Test
    void depositAmountInputTest() {
        final String expected = "123";
        assertEquals(expected, debtPanel.getDepositAmountInput());
    }

    @Test
    void intrestRateTest() {
        final String expected = "5";
        assertEquals(expected, debtPanel.getInterestRate());
    }

    @Test
    void intrestRateToolTest() {
        final String expected = "intrest tool tip";
        assertEquals(expected, debtPanel.getInterestRateTool());
    }

    @Test
    void intrestRateSliderTest() {
        final String expected = "345";
        assertEquals(expected, debtPanel.getInterestRateSlider());
    }

    @Test
    void termLabelTextTest() {
        final String expected = "6";
        assertEquals(expected, debtPanel.getTermLabelText());
    }

    @Test
    void termToolTipTest() {
        final String expected = "term tool tip";
        assertEquals(expected, debtPanel.getTermToolTip());
    }

    @Test
    void termInputFieldTest() {
        final String expected = "456";
        assertEquals(expected, debtPanel.getTermInputField());
    }

    @Test
    void toolTipIconTest() {
        final String expected = "/content/dam/techcombank/rdb-app/images/img1.png";
        assertEquals(expected, debtPanel.getToolTipIcon());
    }

    @Test
    void currencyTextTest() {
        final String expected = "12367";
        assertEquals(expected, debtPanel.getCurrencyText());
    }

    @Test
    void monthTextTest() {
        final String expected = "13";
        assertEquals(expected, debtPanel.getMonthText());
    }

    @Test
    void outputPanelBgTest() {
        final String expected = "/content/dam/techcombank/rdb-app/images/img2.png";
        assertEquals(expected, debtPanel.getOutputPanelBg());
    }

    @Test
    void iconImageTest() {
        final String expected = "/content/dam/techcombank/rdb-app/images/sample-2.bmp";
        assertEquals(expected, debtPanel.getIconImage());
    }

    @Test
    void iconImageAltTest() {
        final String expected = "icon image alt";
        assertEquals(expected, debtPanel.getIconImageAlt());
    }

    @Test
    void interestPaidLabelTest() {
        final String expected = "12";
        assertEquals(expected, debtPanel.getInterestPaidLabel());
    }

    @Test
    void interestPaidOutputAmountTest() {
        final String expected = "677";
        assertEquals(expected, debtPanel.getInterestPaidOutputAmount());
    }

    @Test
    void totalRecievedLabelTest() {
        final String expected = "total recieved";
        assertEquals(expected, debtPanel.getTotalRecievedLabel());
    }

    @Test
    void totalRecievedOutputAmount() {
        final String expected = "4674";
        assertEquals(expected, debtPanel.getTotalRecievedOutputAmount());
    }




}
