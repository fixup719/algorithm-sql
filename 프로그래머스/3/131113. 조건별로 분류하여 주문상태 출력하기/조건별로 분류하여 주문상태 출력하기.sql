-- [문제]
-- 출고여부 칼럼을 생성하여
-- 2022년 5월 1일까지는 출고 완료로
-- 그 이후는 출고 대기로
-- 미정일 경우 출고미정으로 출력
-- 결과는 주문ID 기준 오름차순 정렬

SELECT ORDER_ID, PRODUCT_ID, DATE_FORMAT(OUT_DATE, '%Y-%m-%d'), 
    CASE 
        WHEN OUT_DATE <= '2022-05-01' THEN '출고완료'
        WHEN OUT_DATE > '2022-05-01' THEN '출고대기'
        ELSE '출고미정'
    END AS '출고여부'
FROM FOOD_ORDER
ORDER BY ORDER_ID ASC;