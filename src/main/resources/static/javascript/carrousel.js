import Swiper from "https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.mjs";
const swiper = new Swiper(".mySwiper", {
  effect: "coverflow",
  grabCursor: false,
  centeredSlides: true,
  autoplay: {
    delay: 3000,
  },
  slidesPerView: "3",
  loop: true,
  coverflowEffect: {
    depth: 500,
    modifer: 1,
    slidesShadows: true,
    // rotate:0,
    // stretch:0
  },
});

