import React from 'react';
import './CreateNotice.css';

const CreateNotice = () => {
  return (
    <div>
      <form action="" className="createNotice">
        <div className="cartegory-top">
          <div className="cartegory-top-left">
            <select name="category" className="categoryList">
              <optgroup label="소통">
                <option value="car1">자유게시판</option>
              </optgroup>
              <optgroup label="고객센터">
                <option value="car2">고객의 소리</option>
              </optgroup>
            </select>
            <input type="text" placeholder="제목을 입력하세요" className="title" />
          </div>
          <div className="cartegory-top-right">
            <button className="createBtn">취소</button>
            <button className="createBtn">저장</button>
          </div>
        </div>
        <textarea name="main" className="textBoard"></textarea>
      </form>
    </div>
  );
};

export default CreateNotice;