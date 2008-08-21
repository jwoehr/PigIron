divert(-1)
changecom(`\\')
\\ pigsty.m4
\\   Macros commonly used to autogenerate Java code for PigIron
\\   Copyright *C* 1999, 2001, 2008 Jack J. Woehr
\\   Part of the PigIron Project http://pigiron.sourceforge.net
\\   jwoehr@softwoehr.com

\\ Diversion prepared for restoration
\\ USAGE: push_divert(streamName)
\\ DEFINES: `temp_diversion'
define(`push_divert',`dnl
pushdef(`temp_diversion', divnum)dnl
divert($1)dnl
')

\\ Restore previous pushed diversion
\\ USAGE: pop_divert()
\\ DEPENDS: `temp_diversion'
define(`pop_divert',`dnl
divert(temp_diversion)dnl
popdef(`temp_diversion')dnl
')

\\ A date stamp
define(`date_string',`esyscmd(/bin/date -u)')dnl
changecom()dnl
undivert()dnl

\\ Recognize that a type ends in the string `Array' and treat
\\ it as an Array type in output composition, use VSMArray.modelArray() .
\\ Returns -1 if not a match.
define(`is_type_named_array',`regexp(`$1',`Array$')')

\\ Uppercase a character
define(`upcase', `translit(`$*', `a-z', `A-Z')')

\\ Convert an xcc_xcc.. (etc.) to xccXcc...
define(`javaize2_regexp', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)$')
define(`javaize3_regexp', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)$')
define(`javaize4_regexp', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)$')
define(`javaize2', `regexp(`$1', javaize2_regexp, `upcase(`\1')`\2'upcase(`\3')`\4'')')
define(`javaize3', `regexp(`$1', javaize3_regexp, `upcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'')')
define(`javaize4', `regexp(`$1', javaize4_regexp, `upcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'upcase(`\7')`\8'')')
define(`javaize',  `ifelse(regexp(`$1', javaize4_regexp),`-1',`ifelse(regexp(`$1', javaize3_regexp),`-1',`javaize2(`$1')',`javaize3(`$1')')',`javaize4(`$1')')')
