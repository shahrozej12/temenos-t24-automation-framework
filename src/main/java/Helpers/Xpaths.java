package Helpers;

public class Xpaths {
    // T24 Environment
    public static String T24_Username = "//input[@id='signOnName']";
    public static String T24_Password = "//input[@id='password']";
    public static String T24_SignIn = "//input[@id='sign-in']";
    public static String T24_Text = "//input[@id='commandValue']";
    public static String T24_TickButton = "//img[@id='cmdline_img']";
    public static String T24_Customer_Text = "//a[normalize-space()='CUSTOMER,']/../..//input[@id='transactionId']";
    public static String T24_Edit_Customer = "//img[@title='Edit a contract']";
    public static String T24_Commit_Customer = "//img[@title='Commit the deal']";
    public static String T24_SignOff = "a[title='Sign off']";

    // Customer Fields
    public static String MNEMONIC = "//a[@id='fieldCaption:MNEMONIC']//following::input[@id='fieldName:MNEMONIC']";
    public static String Short_Name = "//a[@id='fieldCaption:SHORT.NAME']//following::input[@id='fieldName:SHORT.NAME:1']";
    public static String GB_Name = "//a[@id='fieldCaption:NAME.1']//following::input[@id='fieldName:NAME.1:1']";
    public static String Sector = "//a[@id='fieldCaption:SECTOR']//following::input[@id='fieldName:SECTOR']";
    public static String Language = "//a[@id='fieldCaption:LANGUAGE']//following::input[@id='fieldName:LANGUAGE']";
    public static String GB_Street = "//a[@id='fieldCaption:STREET']//following::input[@id='fieldName:STREET:1']";
    public static String Post_Code = "//a[@id='fieldCaption:POST.CODE']//following::input[@id='fieldName:POST.CODE:1']";
    public static String Account_Officer = "//a[@id='fieldCaption:ACCOUNT.OFFICER']//following::input[@id='fieldName:ACCOUNT.OFFICER']";
    public static String Industry = "//a[@id='fieldCaption:INDUSTRY']//following::input[@id='fieldName:INDUSTRY']";
    public static String Target = "//a[@id='fieldCaption:TARGET']//following::input[@id='fieldName:TARGET']";
    public static String Nationality = "//a[@id='fieldCaption:NATIONALITY']//following::input[@id='fieldName:NATIONALITY']";
    public static String Customer_Status = "//a[@id='fieldCaption:CUSTOMER.STATUS']//following::input[@id='fieldName:CUSTOMER.STATUS']";
    public static String Residence = "//a[@id='fieldCaption:RESIDENCE']//following::input[@id='fieldName:RESIDENCE']";

}
