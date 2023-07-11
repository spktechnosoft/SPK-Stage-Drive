<#-- @ftlvariable name="" type="com.stagedriving.modules.user.views.email.AccountRecoverEmailNotificationView" -->
<#include "../../common/header.ftl">

<div style="background-color:#FAFAFA;">
    <div class="block-grid " style="Margin: 0 auto; min-width: 320px; max-width: 600px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: #FAFAFA;">
        <div style="border-collapse: collapse;display: table;width: 100%;background-color:#FAFAFA;">
            <!--[if (mso)|(IE)]><table width="100%" cellpadding="0" cellspacing="0" border="0" style="background-color:#FAFAFA;"><tr><td align="center"><table cellpadding="0" cellspacing="0" border="0" style="width:600px"><tr class="layout-full-width" style="background-color:#FAFAFA"><![endif]-->
            <!--[if (mso)|(IE)]><td align="center" width="600" style="background-color:#FAFAFA;width:600px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;" valign="top"><table width="100%" cellpadding="0" cellspacing="0" border="0"><tr><td style="padding-right: 0px; padding-left: 0px; padding-top:0px; padding-bottom:0px;"><![endif]-->
            <div class="col num12" style="min-width: 320px; max-width: 600px; display: table-cell; vertical-align: top; width: 600px;">
                <div style="width:100% !important;">
                    <!--[if (!mso)&(!IE)]><!-->
                    <div style="border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;">
                        <!--<![endif]-->

                        <!--[if mso]><table width="100%" cellpadding="0" cellspacing="0" border="0"><tr><td style="padding-right: 50px; padding-left: 50px; padding-top: 30px; padding-bottom: 15px; font-family: Tahoma, Verdana, sans-serif"><![endif]-->
                        <div style="color:#535353;font-family:Tahoma, Verdana, Segoe, sans-serif;line-height:1.5;padding-top:30px;padding-right:50px;padding-bottom:15px;padding-left:50px;">
                            <div style="font-family: Tahoma, Verdana, Segoe, sans-serif; font-size: 13px; line-height: 1.5; color: #535353; mso-line-height-alt: 20px; mso-ansi-font-size: 14px;">
                                <p style="font-size: 15px; line-height: 1.5; text-align: center; mso-line-height-alt: 23px; margin: 0;"><span style="font-size: 15px;">Ciao ${user.firstname},</span><br><span style="font-size: 15px;">la tua nuova password è ${password}</span></p>
                            </div>
                        </div>
                        <!--[if mso]></td></tr></table><![endif]-->
                        <!--[if (!mso)&(!IE)]><!-->
                    </div>
                    <!--<![endif]-->
                </div>
            </div>
            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->
            <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->
        </div>
    </div>
</div>

<#include "../../common/footer.ftl">
