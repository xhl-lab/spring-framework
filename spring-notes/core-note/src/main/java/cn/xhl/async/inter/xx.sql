SELECT ID,
       CRDDHZELG,
    SCORE,PROFDATE,CREATETIME
           FROM(SELECT T.*, T2.FIELD2,
                       row_number() over (partition by cardnbr order by id desc) rn
                from RDS_CRDSCD_DHCARD T, TOPC.TBL_ACM_ACCBSC T2
                where t.cardnbr = T2.AB_ACCTNO)
where rn = 1