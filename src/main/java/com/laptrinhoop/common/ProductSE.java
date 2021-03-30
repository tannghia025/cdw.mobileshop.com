package com.laptrinhoop.common;

import com.laptrinhoop.client.ElasticClientWrite;
import com.laptrinhoop.entity.Product;
import com.laptrinhoop.model.ProductModel;
import com.laptrinhoop.service.IGeneralService;
import com.laptrinhoop.service.IProductService;
import com.rabbitmq.tools.json.JSONUtil;
import joptsimple.internal.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductSE {
    private static String currentIndex="products";

    public String refresh(int id,  IProductService services,int dataCenter) throws Throwable {
        String message ="NOT OK";
        ProductModel productModel = new ProductModel();
        if (id > 0) {
                var product = services.findById(id);
                if (product != null) {
                    productModel.id = product.getId();
                    productModel.name = product.getName();
                    productModel.available = product.getAvailable();
                    productModel.description = product.getDescription();
                    productModel.discount = product.getDiscount();
                    productModel.viewCount = product.getViewCount();
                    productModel.productDate = product.getProductDate();
                    productModel.quantity = product.getQuantity();
                    updateProduct(id + "", productModel,dataCenter);

                    message = "SUCCESSFULLY INIT";
                } else {
                    message = "ID NOT EXIST !!";

                }
        }
        return message;

    }

    private void updateProduct(String id, Object obj,int dataCenter) throws Exception {
        ElasticClientWrite.getInstance(dataCenter).IndexObject(currentIndex, obj, id);

    }
}
