SELECT DISTINCT D.ID, D.EMAIL, D.FIRST_NAME, D.LAST_NAME
FROM DEVELOPERS D, SKILLCODES S
WHERE S.NAME IN ('Python', 'C#') && (D.SKILL_CODE & S.CODE > 0)
ORDER BY D.ID;