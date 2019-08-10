package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage;

import android.content.Context;
import android.util.Log;

import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrandDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductCategory.ProductCategoryDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductGroup.ProductGroup;
import com.salesforce.android.restsample.DB.Model.DBProductGroup.ProductGroupDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPurchasedProduct.PurchasedProduct;
import com.salesforce.android.restsample.DB.Model.DBPurchasedProduct.PurchasedProductDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 9/6/16 AD.
 */
//public class GetProductSinglePage {
//}

import com.salesforce.android.restsample.DB.Model.DBProduct.Product;

/**
 * Created by chukeatboontae on 8/29/16.
 */
public class GetProductSinglePage2 {

    ProductBrandDatabaseHelper mDBBrand;
    ProductCategoryDatabaseHelper mDBCategory;
    ProductGroupDatabaseHelper mDBGroup;
    ProductDatabaseHelper mDBProduct;
    PurchasedProductDatabaseHelper mDBPurchased;

    ArrayList<String> results;
    ArrayList<Integer> resultType;
    ArrayList<String> resultId;
    ArrayList<String> resultPurchasedCode;

    List<Product> productList = new ArrayList<>();

    public GetProductSinglePage2 (Context context, String accountId){
        mDBBrand = new ProductBrandDatabaseHelper(context);
        mDBCategory = new ProductCategoryDatabaseHelper(context);
        mDBGroup = new ProductGroupDatabaseHelper(context);
        mDBProduct = new ProductDatabaseHelper(context);
        mDBPurchased = new PurchasedProductDatabaseHelper(context);

        results = new ArrayList<>();
        resultType = new ArrayList<>();
        resultId = new ArrayList<>();
        resultPurchasedCode = new ArrayList<>();

        ArrayList<String> idCat = new ArrayList<>();
        ArrayList<String> idGroup = new ArrayList<>();

        List<ProductGroup> groups = mDBGroup.getListProductGroupList();

        for (int i=0;i<groups.size();i++) {
            idCat.add(groups.get(i).getPdctid());
            idGroup.add(groups.get(i).getId());
        }

        for (int i=0;i<idCat.size();i++){
            for (int j=(i+1);j<idCat.size();j++){
                if (idCat.get(j).equals(idCat.get(i))){
                    idCat.remove(j);
                    j--;
                }
            }
        }

        ArrayList<String> idBrand = new ArrayList<>();
        for (int i=0;i<idCat.size();i++){
            idBrand.add(mDBCategory.getProductBrandIdBySearchId(idCat.get(i)));
        }

        for (int i=0;i<idBrand.size();i++){
            for (int j=(i+1);j<idBrand.size();j++){
                if (idBrand.get(j).equals(idBrand.get(i))) {
                    idBrand.remove(j);
                    j--;
                }
            }
        }

        List<PurchasedProduct> purchasedProductList = mDBPurchased.getListPurchasedProductListBySearchAccId(accountId);
        for (int i=0;i<purchasedProductList.size();i++){
            results.add("purchased");
            results.add(mDBProduct.getNameBySearchId(purchasedProductList.get(i).getProdCode()));
            resultPurchasedCode.add(purchasedProductList.get(i).getProdCode());
            results.add("groupname");
            List<Product> productList = mDBProduct.getListProductListBySearchCode(purchasedProductList.get(i).getProdCode());
            results.add(mDBGroup.getProductGroupNameBySearchId(productList.get(0).getIdPdgr()));
        }

        for (int i=0;i<idBrand.size();i++){
            results.add("section");
            results.add(mDBBrand.getProductBrandNameBySearchId(idBrand.get(i)));
            results.add("color");
            if (mDBBrand.getProductBrandColorBySearchId(idBrand.get(i)).equals(" ") ||
                    mDBBrand.getProductBrandColorBySearchId(idBrand.get(i)).isEmpty() ){
                results.add("none");
            }else {
                results.add(mDBBrand.getProductBrandColorBySearchId(idBrand.get(i)));
            }
            for (int j=0;j<idCat.size();j++){
                if (!mDBCategory.getProductCategoryNameBySearchIdAtBrId(idCat.get(j),idBrand.get(i)).equals(" ")) {
                    results.add("sub-section");
                    results.add(mDBCategory.getProductCategoryNameBySearchIdAtBrId(idCat.get(j), idBrand.get(i)));
                    for (int k=0;k<groups.size();k++){
                        productList = mDBProduct.getListProductListByBrCatGr(idBrand.get(i),idCat.get(j),idGroup.get(k));
                        for (int n=0;n<productList.size();n++){
                            int check = 0;
                            for (int m=0;m<resultPurchasedCode.size();m++) {
//                                Log.e("chkProdName","chkProdName_"+mDBProduct.getNameByGroup(idBrand.get(i),idCat.get(j),idGroup.get(k))+ " | _"+mDBProduct.getNameBySearchId(resultPurchasedCode.get(m)));
                                if (productList.get(n).getName().equals(mDBProduct.getNameBySearchId(resultPurchasedCode.get(m)))) {
                                    check = 1;
                                    break;
                                }
                            }
                            if (check == 0) {
                                results.add("items");
                                results.add(productList.get(n).getName());
                                results.add("groupname");
                                results.add(mDBGroup.getProductGroupNameBySearchId(idGroup.get(k)));
                            }
                        }
                    }
                }
            }
        }

        for (int i=0;i<results.size();i++){
            if (i==0){
                resultType.add(i,0);
            }else {
                if (results.get(i-1).equals("purchased")) {
                    resultType.add(i, 1);
                } else if (results.get(i-1).equals("color")) {
                    resultType.add(i, 2); // old=2
                } else if (results.get(i-1).equals("section")) {
                    resultType.add(i, 3); // old=3
                } else if (results.get(i-1).equals("sub-section")) {
                    resultType.add(i, 4);
                } else if (results.get(i-1).equals("items")) {
                    resultType.add(i, 5);
                } else if (results.get(i-1).equals("groupname")) {
                    resultType.add(i, 6);
                } else {
                    resultType.add(i, 0);
                }
            }
        }

        for (int i=0;i<results.size();i++){
            if (i==0){
                resultId.add(i,"");
            }else {
                if (results.get(i - 1).equals("purchased")) {
                    resultId.add(i, mDBProduct.getCodeBySearchName(results.get(i)));
                } else if (results.get(i - 1).equals("section")) {
                    resultId.add(i, mDBBrand.getProductBrandidBySearchName(results.get(i)));
                } else if (results.get(i - 1).equals("color")) {
                    resultId.add(i, results.get(i));
                } else if (results.get(i - 1).equals("sub-section")) {
                    resultId.add(i, mDBCategory.getProductCatIdBySearchNameCat(results.get(i)));
                } else if (results.get(i - 1).equals("items")) {
                    resultId.add(i, mDBProduct.getCodeBySearchName(results.get(i)));
                } else {
                    resultId.add(i,"");
                }
            }
        }

//        for (int i=0; i<resultType.size();i++){
//            Log.e("chkType","_"+i+"_"+resultType.get(i));
//        }
//
//        for (int i=0; i<resultId.size();i++){
//            Log.e("chkId","_"+i+"_"+resultId.get(i));
//        }
//
        for (int i=0; i<results.size();i++){

             Log.e("chkResult","chkResult:_i-2 = "+i+"_results_"+results.get(i)+" | type_"+resultType.get(i)+" | id_"+resultId.get(i));

            // the Last: chkResult:_i-2 = 327_results_BIOS LIFE C (U) | type_6 | id_
        }
    }

    public ArrayList<String> getResults() {
        return results;
    }

    public ArrayList<Integer> getResultType() {
        return resultType;
    }

    public ArrayList<String> getResultId() {
        return resultId;
    }
}

