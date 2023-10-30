import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Carousel } from "react-responsive-carousel";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { useNavigate } from "react-router-dom";
import { call } from "../../ApiService";
import { API_BASE_URL } from "../../ApiConfig";
import { MdVisibility } from "react-icons/md";
import { MdFavoriteBorder } from "react-icons/md";

const CarouselBox = () => {
  // const state = useSelector((state) => state.product);
  const [currentIndex, setCurrentIndex] = useState();
  const navigate = useNavigate();
  const [randomDTO, setRandomDTO] = useState([]);

  useEffect(() => {
    // DB에서 랜덤 10개 상품 가져오기
    call("/main/searchrandom", "GET").then((response) => {
      if (response.error && response.error != null) {
        alert(response.error);
        return;
      }
      setRandomDTO(response.data);
    });
  }, []);

  // const sliceState = state.slice(10, 20);
  // const imageBox = sliceState.map((s) => (
  // <div key={s.id} className="slide-item">
  //   <div>
  //     <img src={s.imgsrc1} alt={s.name} />
  //   </div>
  //   <div className="slide-textBox">
  //     <div className="slide-title">
  //       <p className="slide-text">{s.name}</p>
  //     </div>
  //     <p className="slide-text">{s.price}</p>
  //     <p className="slide-text">
  //       {Math.floor(
  //         (new Date().getTime() - Date.parse(s.uptime)) / 1000 / 60
  //       )}
  //       분전
  //     </p>
  //   </div>
  // </div>
  // ));
  const imageBox = randomDTO.map((dto) => (
    <div key={dto.id} className="slide-item">
      <div>
        <img src={API_BASE_URL + dto.imgUrl} alt={dto.title} />
      </div>
      <div className="slide-textBox">
        <div className="slide-title">
          <p className="slide-text">{dto.title}</p>
        </div>
        <p className="slide-text">{dto.price}원</p>
        <div
          className="slide-text"
          // style={{ display: "flex", justifyContent: "space-between" }}
        >
          {Math.floor(
            (new Date().getTime() - Date.parse(dto.updateTime)) / 1000 / 60
          ) < 60 ? (
            <div>
              {Math.floor(
                (new Date().getTime() - Date.parse(dto.updateTime)) / 1000 / 60
              )}
              분전
            </div>
          ) : Math.floor(
              (new Date().getTime() - Date.parse(dto.updateTime)) /
                1000 /
                60 /
                60
            ) < 24 ? (
            <div>
              {Math.floor(
                (new Date().getTime() - Date.parse(dto.updateTime)) /
                  1000 /
                  60 /
                  60
              )}
              시간전
            </div>
          ) : (
            <div>
              {Math.floor(
                (new Date().getTime() - Date.parse(dto.updateTime)) /
                  1000 /
                  60 /
                  60 /
                  24
              )}
              일전
            </div>
          )}
        </div>
        <p className="slide-text">
          <MdVisibility style={{ fontSize: "14px" }} /> {dto.clickedCount}{" "}
          <MdFavoriteBorder style={{ fontSize: "14px" }} /> {dto.wishedCount}
        </p>
      </div>
    </div>
  ));

  const handleChange = (index) => {
    setCurrentIndex(index);
  };

  const handleClick = (index, elem) => {
    navigate("/detail/" + randomDTO[index].id);
  };

  return (
    <Carousel
      showArrows={true}
      showStatus={false}
      showIndicators={false}
      autoPlay={true}
      infiniteLoop={true}
      showThumbs={false}
      selectedItem={randomDTO[currentIndex]}
      onChange={handleChange}
      onClickItem={handleClick}
      centerMode={true}
      centerSlidePercentage={35}
      className="carousel-style"
    >
      {imageBox}
    </Carousel>
  );
};

export default CarouselBox;
