package rw.productant.v1.common.constants;


import rw.productant.v1.common.exceptions.BadRequestException;

public interface ConstantsHandler {
    public  String DEFAULT_PAGE_NUMBER = "0";
    public  String DEFAULT_PAGE_SIZE = "100";
    public  int MAX_PAGE_SIZE = 1000;


    public static void validatePageNumberAndSize(int pageNumber, int pageSize) {
        if (pageNumber < 1) {
            throw new BadRequestException("Page number is less than zero.");
        }

        if (pageSize > ConstantsHandler.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size is greater than " + ConstantsHandler.MAX_PAGE_SIZE);
        }
    }
}
