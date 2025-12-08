package kz.javazhan.kolesa.mappers;

import kz.javazhan.kolesa.entities.DTO.SellerDTO;
import kz.javazhan.kolesa.entities.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    SellerDTO toSellerDTO(Seller seller);

    Seller toSeller(SellerDTO sellerDTO);
}
