define(`upcase', `translit(`$*', `a-z', `A-Z')')dnl
define(`javaname3', `regexp(`$1', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)\.m4$', `upcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'`.java'')')dnl
define(`javaname4', `regexp(`$1', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)\.m4$', `upcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'upcase(`\7')`\8'`.java'')')dnl
define(`javaname', `ifelse(regexp(`$1', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)\.m4$'),`-1',`javaname3(`$1')',`javaname4(`$1')')')dnl
