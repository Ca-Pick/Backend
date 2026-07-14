-- 1. 가게 데이터
INSERT INTO cake_store (
    id,
    instagram_url,
    name,
    address,
    latitude,
    longitude
) VALUES
      (1, 'https://www.instagram.com/deflora_cake/', 'Deflora', '서울시 강남구 봉은사로 26길 28 1층', 37.505373, 127.037142),
      (2, 'https://www.instagram.com/saenguabang_bomnal/', '생과방 봄날', '서울 서초구 강남대로2길 75 학성빌딩 101호 sweet bomnal', 37.47648, 127.03451);

-- 2. 케이크 레퍼런스 데이터
INSERT INTO cake_reference (
    id,
    cake_store_id,
    instagram_embed,
    place,
    shape,
    color
) VALUES
      (1, 1, 'https://www.instagram.com/p/DFxF4K8yG6D/J2', '강남', '기본형', '파스텔'),
      (2, 2, 'https://www.instagram.com/p/DaDC3ckTr7v/?utm_source=ig_web_copy_link', '강남', '입체형', '파스텔');


-- 3. 타켓 데이터
INSERT INTO target_reference (
    cake_reference_id,
    target
) VALUES
      (1, '친구'),
      (1, '연인'),
      (1, '서포트'),
      (2, '가족'),
      (2, '연인'),
      (2, '서포트');

-- 3. 분위기 데이터
INSERT INTO mood_reference (
    cake_reference_id,
    mood
) VALUES
      (1, '귀여운'),
      (1, '개성있는'),
      (2, '심플'),
      (2, '개성있는');

-- 4. 세부 장식 데이터
INSERT INTO detail_reference (
    cake_reference_id,
    decoration
) VALUES
      (1, '리본'),
      (1, '시스루'),
      (1, '블랙리본'),
      (1, '하트'),
      (1, '블랙'),
      (1, '핑크'),
      (1, '블랙핑크'),
      (1, '실타래크림'),
      (1, '레터링'),
      (1, '러블리'),
      (1, '시크'),
      (1, '키치'),
      (2, '달항아리'),
      (2, '전통'),
      (2, '꽃'),
      (2, '벚꽃'),
      (2, '나비'),
      (2, '보라'),
      (2, '화이트'),
      (2, '라벤더'),
      (2, '플라워'),
      (2, '오브제'),
      (2, '도자기'),
      (2, '한식디저트'),
      (2, '고급스러운'),
      (2, '단아한'),
      (2, '봄'),
      (2, '생화'),
      (2, '화병'),
      (2, '청자'),
      (2, '돌띠');
