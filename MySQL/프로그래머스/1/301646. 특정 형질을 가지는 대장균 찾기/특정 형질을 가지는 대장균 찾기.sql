SELECT COUNT(*) AS COUNT
FROM ECOLI_DATA
WHERE 1=1   -- 항상 참이므로 이후 조건을 쉽게 추가할 수 있음
  AND GENOTYPE & 2 != 2
  AND (GENOTYPE & 4 = 4 OR GENOTYPE & 1 = 1);
