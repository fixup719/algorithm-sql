-- [문제]
-- 2022년 10월 16일에 자동차가 대여 중이라면 대여중, 그게 아니라면 대여가능 출력
-- 자동차 ID기준 내림차순 정렬
SELECT CAR_ID,
    CASE 
        WHEN CAR_ID IN (SELECT CAR_ID 
                        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                        WHERE '2022-10-16' BETWEEN START_DATE AND END_DATE) THEN '대여중'
        ELSE '대여 가능'
    END 'AVAILABILITY'
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
ORDER BY CAR_ID DESC;  