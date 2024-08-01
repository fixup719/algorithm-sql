-- [문제]
-- 보호 시작일보다 입양일이 더 빠른 동물의 아이디와 이름 조회
-- ANIMAL_INS : 보호소에 들어온 동물 정보
-- ANIMAL_OUTS : 보호소에서 입양보낸 동물 정보
SELECT I.ANIMAL_ID, I.NAME
FROM ANIMAL_INS I JOIN ANIMAL_OUTS O ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE I.DATETIME > O.DATETIME
ORDER BY I.DATETIME ASC;