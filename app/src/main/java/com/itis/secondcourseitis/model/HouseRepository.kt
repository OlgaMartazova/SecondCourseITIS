package com.itis.secondcourseitis.model

object HouseRepository {
    var id = 0
    val houses = arrayListOf (
        House(id,"Barn", "small house", listOf("https://static.wixstatic.com/media/d43eb5_708eb0a453ba4cc28ff3dc6e0f0973f5~mv2.jpg/v1/fill/w_320,h_385,q_90/d43eb5_708eb0a453ba4cc28ff3dc6e0f0973f5~mv2.jpg", "https://dkmk.ru/images/barnwoodenhouse.jpeg", "https://smartdom5.ru/img/19399376_1920_q70.jpg")),
        House(id++,"Apartment", "big house", listOf("https://media-cdn.tripadvisor.com/media/photo-s/06/39/d2/bd/premium-apartment-house.jpg", "https://call2view.com.au/assets/image-cache/uploads/images/0_23-0.Apartment-vs-House.aa5b55c5.jpg")),
        House(id++, "Bungalow", "a low house having only one storey or, in some cases, upper rooms set in the roof, typically with dormer windows", listOf("https://cdn.vox-cdn.com/thumbor/frFQQhOsxl8DctGjkR8OLHpdKMs=/0x0:3686x2073/1200x800/filters:focal(1549x743:2137x1331)/cdn.vox-cdn.com/uploads/chorus_image/image/68976842/House_Tour_Liverman_3D6A3138_tour.0.jpg", "https://pix10.agoda.net/hotelImages/544190/-1/fe281e7414fe236eabfd6ec98a4016e9.jpg?s=1024x768", "https://pix10.agoda.net/hotelImages/544/544900/544900_13103015260017254641.jpg?s=1024x768", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/316040856.jpg?k=fc268ab75f91d3566447a23c1bb5f33a342433553c582ebeb68250cfd7ec42b0&o=&hp=1")),
        House(id++, "Castle", "a large building, typically of the medieval period", listOf("https://www.gannett-cdn.com/-mm-/77ffef65e43a5289ce0aeaa4e3baa821ef6cfd72/c=0-368-3985-2620/local/-/media/2018/07/30/TennGroup/Nashville/636685567315326900-6008-Hillsboro-drone-02-hires.jpg?width=3200&height=1809&fit=crop&format=pjpg&auto=webp", "https://www.gannett-cdn.com/presto/2021/09/09/PDTF/a375a5fa-868c-4050-bf12-69be1dc42348-1_1_of_28.jpg?crop=1997,1124,x1,y0&width=660&height=372&format=pjpg&auto=webp", "https://i.insider.com/54a6c2806bb3f7b3698b456d?width=600&format=jpeg&auto=webp", "https://i.insider.com/5102b07e6bb3f7f105000006?width=600&format=jpeg")),
    )
}
