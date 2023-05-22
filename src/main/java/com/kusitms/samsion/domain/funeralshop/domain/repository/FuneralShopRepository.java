package com.kusitms.samsion.domain.funeralshop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusitms.samsion.domain.funeralshop.domain.entity.FuneralShop;

public interface FuneralShopRepository extends JpaRepository<FuneralShop, Long>,FuneralShopRepositoryCustom {
}
