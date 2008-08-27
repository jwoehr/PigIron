define(`upcase', `translit(`$*', `a-z', `A-Z')')dnl
define(`javaname2_regexp', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)\.m4$')dnl
define(`javaname3_regexp', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)\.m4$')dnl
define(`javaname4_regexp', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)\.m4$')dnl
define(`javaname2', `regexp(`$1', javaname2_regexp, `upcase(`\1')`\2'upcase(`\3')`\4'`.java'')')dnl
define(`javaname3', `regexp(`$1', javaname3_regexp, `upcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'`.java'')')dnl
define(`javaname4', `regexp(`$1', javaname4_regexp, `upcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'upcase(`\7')`\8'`.java'')')dnl
define(`javaname',  `ifelse(regexp(`$1', javaname4_regexp),`-1',`ifelse(regexp(`$1', javaname3_regexp),`-1',`javaname2(`$1')',`javaname3(`$1')')',`javaname4(`$1')')')dnl
