import React from "react";
import "./Detail.css";
import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";

const Detail = () => {
  const { id } = useParams();
  const product = useSelector((state) => state.product);
  const user = useSelector((state) => state.user);
  const item = product.find((p) => p.id === id);
  const userproduct = useSelector((state) => state.user[1].userproduct);
  const itemcate = product.find((i) => i.categories === item.categories);
  const recomends = product.filter((j) => j.categories === itemcate.categories);

  console.log("item :" + item.categories);
  console.log("itemcate:" + itemcate);
  console.log("recomends: " + recomends[1].imgsrc1);

  return (
    <div className="container">
      <div className="contentsBox">
        <div className="contentsBox2">
          <div className="products">
            <div className="imgBox">
              <img className="imgBox" src={item.imgsrc1} alt="1" />
            </div>
          </div>
          <div className="products">
            <div>
              <p>제품명:{item.name}</p>
            </div>
            <div>
              <p>가격:{item.price}</p>
            </div>
            <div>
              <button>채팅 하기</button>
            </div>
            <div>
              <button>찜</button>
            </div>
          </div>
        </div>

        <div className="contentsBox2">
          <div className="products">
            <div>
              <h2>상품 내용</h2>
              <div>{item.text}</div>
            </div>
          </div>

          <div className="products">
            <div>
              <h2>새싹 정보</h2>
              <div>닉네임: {user[1].nickname}</div>
            </div>
            <div>
              <h2>닉네임 님의 다른 판매상품 정보</h2>
              <div className="detail-imgbox-flex">
                {userproduct.slice(0, 3).map((up) => (
                  <div className="detail-itembox">
                    <div className="detail-imgbox1">
                      <img className="detail-imgbox1" src={up.imgsrc1} alt="" />
                    </div>
                    <p>상품명:{up.name}</p>
                    <h5>가격:{up.price}</h5>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>

        <div className="contentsBox2">
          <div className="products">
            <h1>거래 희망 장소</h1>
            지도이미지 큰거
          </div>
        </div>

        <div className="contentsBox2">
          <div className="products">
            <h1>이런 상품은 어때요?</h1>

            {recomends.slice(0, 4).map((e) => (
              <div>
                <div>
                  <img src={e.imgsrc1} alt="" srcset="" />
                </div>
                <p>제품명:{e.name}</p>
                <p>제품가격:{e.price}</p>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Detail;