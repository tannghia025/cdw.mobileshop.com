//package com.laptrinhoop.common;
//
//import com.laptrinhoop.client.ElasticClientWrite;
//import com.laptrinhoop.model.CategoryModel;
//import com.laptrinhoop.model.ProductModel;
//import com.laptrinhoop.service.ICategoryService;
//import com.laptrinhoop.service.IProductService;
//
//public class CategorySE {
//    private static String currentIndex="categories";
//
//    public String refresh(int id, ICategoryService services, int dataCenter) throws Throwable {
//        String message ="NOT OK";
//        CategoryModel categoryModel = new CategoryModel();
//        if (id > 0) {
//            var product = services.findById(id);
//            if (product != null) {
//                productModel.id = product.getId();
//                productModel.name = product.getName();
//                productModel.available = product.getAvailable();
//                productModel.description = product.getDescription();
//                productModel.discount = product.getDiscount();
//                productModel.viewCount = product.getViewCount();
//                productModel.productDate = product.getProductDate();
//                productModel.quantity = product.getQuantity();
//                updateProduct(id + "", productModel,dataCenter);
//
//                message = "SUCCESSFULLY INIT";
//            } else {
//                message = "ID NOT EXIST !!";
//
//            }
//        }
//        return message;
//
//    }
//
//    private void updateProduct(String id, Object obj,int dataCenter) throws Exception {
//        ElasticClientWrite.getInstance(dataCenter).IndexObject(currentIndex, obj, id);
//
//    }
//}
