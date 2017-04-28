package cph.singersw.cphos;

/**
 * Created by Drackjoie on 28/4/2560.
 */

public class MyConstant {

    private String urlGetProductWhereQR = "http://swiftcodingthai.com/cph/getProductWhereQRSuwat.php";

    private String[]columnProduct = new String[]{"id","Name","QR_code","id_Receive",
            "Description","Date_Receive"};

    private String urlGetUserWhereID = "http://swiftcodingthai.com/cph/getUserWhereID.php";



    public String getUrlGetUserWhereID() {
        return urlGetUserWhereID;
    }

    public String[] getColumnProduct() {
        return columnProduct;
    }

    public String getUrlGetProductWhereQR() {

        return urlGetProductWhereQR;
    }//Meethod Getter
}//Main Class
