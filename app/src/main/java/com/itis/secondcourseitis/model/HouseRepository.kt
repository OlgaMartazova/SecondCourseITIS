package com.itis.secondcourseitis.model

object HouseRepository {
    var id = 0
    val houses = arrayListOf (
        House(id,"Barn", "small house", listOf("https://static.wixstatic.com/media/d43eb5_708eb0a453ba4cc28ff3dc6e0f0973f5~mv2.jpg/v1/fill/w_320,h_385,q_90/d43eb5_708eb0a453ba4cc28ff3dc6e0f0973f5~mv2.jpg", "https://dkmk.ru/images/barnwoodenhouse.jpeg", "https://smartdom5.ru/img/19399376_1920_q70.jpg")),
        House(id++,"Apartment", "in big house", listOf("https://media-cdn.tripadvisor.com/media/photo-s/06/39/d2/bd/premium-apartment-house.jpg", "https://call2view.com.au/assets/image-cache/uploads/images/0_23-0.Apartment-vs-House.aa5b55c5.jpg")),
    )
}
