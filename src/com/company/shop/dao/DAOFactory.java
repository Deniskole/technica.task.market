package com.company.shop.dao;

import java.io.File;

public class DAOFactory {

    public static GoodsDAO getProductDao(File f) {
        return GoodsDAOImpl.getInctance(f);
    }

}
