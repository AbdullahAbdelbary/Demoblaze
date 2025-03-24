package org.example;

import org.testng.Assert;

public class AboutusPage extends BasePage{
    //************************************** Elements Positions ***********************************************
    private final String aboutusButtonCSS = "#navbarExample > ul > li:nth-child(3) > a";
    private final String vedioCSS = "#example-video > div.vjs-error-display.vjs-modal-dialog > div";
    private final String XSignCSS = "#videoModal > div > div > div.modal-header > button > span";
    private final String closeButtonCSS = "#videoModal > div > div > div.modal-footer > button";


    //******************************************** Logic *******************************************************
    public void aboutusValidation(String closeWay) throws InterruptedException {
        waitThenClickCSS(aboutusButtonCSS, 10);
        waitThenClickCSS(vedioCSS, 10);
        Assert.assertTrue(isVideoClickableAndPlaying(vedioCSS));
        if (closeWay.equals("X")) waitThenClickCSS(XSignCSS, 10);
        if (closeWay.equals("close")) waitThenClickCSS(closeButtonCSS, 10);
    }
}
