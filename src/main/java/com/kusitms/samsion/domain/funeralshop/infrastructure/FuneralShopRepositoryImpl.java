package com.kusitms.samsion.domain.funeralshop.infrastructure;

import com.kusitms.samsion.domain.funeralshop.domain.entity.FuneralShop;
import com.kusitms.samsion.domain.funeralshop.domain.entity.QFuneralShop;
import com.kusitms.samsion.domain.funeralshop.domain.repository.FuneralShopRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FuneralShopRepositoryImpl implements FuneralShopRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<FuneralShop> findFuneralShopList(Pageable pageable, List<String> areaTagList) {
        List<FuneralShop> funeralShopList = jpaQueryFactory
                .select(QFuneralShop.funeralShop).distinct()
                .from(QFuneralShop.funeralShop)
                .where(getTagsInQuery(areaTagList))
                .orderBy(QFuneralShop.funeralShop.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return getSliceImpl(funeralShopList, pageable);
    }

    private BooleanExpression getTagsInQuery(List<String> areaTagList) {
        if (areaTagList == null || areaTagList.isEmpty()) {
            return null;
        }

        if (areaTagList.contains("전체")) {
            return null; // 전체를 클릭하면 조건을 생략하여 전체 데이터를 반환
        }

        StringPath areaPath = QFuneralShop.funeralShop.area;
        return areaPath.in(areaTagList);
    }

    private <T> Slice<T> getSliceImpl(List<T> list, Pageable pageable){
        boolean hasNext = false;
        if (list.size() > pageable.getPageSize()) {
            hasNext = true;
            list.remove(list.size() - 1);
        }

        return new SliceImpl<>(list, pageable, hasNext);
    }
}
