-- 문제 요약 : 조회수가 가장 높은 중고 거래 게시물에 대한 첨부파일 경로 조회
-- 파일경로 = /home/grep/src/파일id/파일이름/파일확장자
SELECT CONCAT('/home/grep/src/', B.BOARD_ID, '/', F.FILE_ID, F.FILE_NAME, F.FILE_EXT) AS 'FILE_PATH'
FROM USED_GOODS_BOARD B JOIN USED_GOODS_FILE F ON B.BOARD_ID = F.BOARD_ID
WHERE B.VIEWS = (SELECT MAX(VIEWS) FROM USED_GOODS_BOARD)
ORDER BY F.FILE_ID DESC;