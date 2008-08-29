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
define(`date_string',`esyscmd(/bin/date -u)')

\\ Recognize that a type ends in the string `Array' and treat
\\ it as an Array type in output composition, use VSMArray.modelArray() .
\\ Returns -1 if not a match.
define(`is_type_named_array',`regexp(`$1',`Array$')')

\\ Uppercase a character
define(`upcase', `translit(`$*', `a-z', `A-Z')')
\\ lowercase a character
define(`lowcase', `translit(`$*', `A-Z', `a-z')')

\\ Convert an xcc_xcc.. (etc.) to XccXcc...
define(`javaize2_regexp', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)$')
define(`javaize3_regexp', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)$')
define(`javaize4_regexp', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)$')
define(`javaize5_regexp', `^\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)_\(\w\)\(\w*\)$')
define(`javaize2', `regexp(`$1', javaize2_regexp, `upcase(`\1')`\2'upcase(`\3')`\4'')')
define(`javaize3', `regexp(`$1', javaize3_regexp, `upcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'')')
define(`javaize4', `regexp(`$1', javaize4_regexp, `upcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'upcase(`\7')`\8'')')
define(`javaize5', `regexp(`$1', javaize5_regexp, `upcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'upcase(`\7')`\8'upcase(`\9')`\10'')')
define(`javaize',  `ifelse(regexp(`$1', javaize5_regexp),`-1',`ifelse(regexp(`$1', javaize4_regexp),`-1',`ifelse(regexp(`$1', javaize3_regexp),`-1',`javaize2(`$1')',`javaize3(`$1')')',`javaize4(`'$1)')',`javaize5(`$1')')')

\\ Convert an xcc_xcc.. (etc.) to xccXcc...
define(`javaize2lc', `regexp(`$1', javaize2_regexp, `lowcase(`\1')`\2'upcase(`\3')`\4'')')
define(`javaize3lc', `regexp(`$1', javaize3_regexp, `lowcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'')')
define(`javaize4lc', `regexp(`$1', javaize4_regexp, `lowcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'upcase(`\7')`\8'')')
define(`javaize5lc', `regexp(`$1', javaize5_regexp, `lowcase(`\1')`\2'upcase(`\3')`\4'upcase(`\5')`\6'upcase(`\7')`\8'upcase(`\9')`\10'')')
define(`javaize_lc',  `ifelse(regexp(`$1', javaize5_regexp),`-1',`ifelse(regexp(`$1', javaize4_regexp),`-1',`ifelse(regexp(`$1', javaize3_regexp),`-1',`javaize2lc(`$1')',`javaize3lc(`$1')')',`javaize4lc(`$1')')',`javaize5lc(`$1')')')
changecom()

\\ param_namespace(`entity', `associated_function')
define(`param_namespace',`dnl
pushdef(`param_entity_name',`$1')dnl
pushdef(`associated_function',`$2')dnl
pushdef(`structure_formal_name', param_entity_name`_structure')dnl
pushdef(`counted_structure_formal_name', structure_formal_name`_counted')dnl
pushdef(`array_formal_name', param_entity_name`_array')dnl
pushdef(`structure_classname', javaize(structure_formal_name))dnl
pushdef(`counted_structure_classname', javaize(counted_structure_formal_name))dnl
pushdef(`array_classname', javaize(array_formal_name))dnl
')

\\ param_namespace_end()
define(`param_namespace_end',`dnl
popdef(`array_classname')dnl
popdef(`counted_structure_classname')dnl
popdef(`structure_classname')dnl
popdef(`structure_formal_name')dnl
popdef(`associated_function')dnl
popdef(`param_entity_name')dnl
')

\\ function_namespace(`function_formal_name', `significant_parameter_formal_name')
define(`function_namespace',`dnl
pushdef(`function_formal_name',`$1')dnl
pushdef(`significant_parameter_formal_name',`$2')dnl
pushdef(`function_classname', javaize(function_formal_name))dnl
pushdef(`significant_parameter_classname', javaize(significant_parameter_formal_name))dnl
')

\\ function_namespace_end()
define(`function_namespace_end',`dnl
popdef(`function_classname')dnl
popdef(`significant_parameter_formal_name')dnl
popdef(`significant_parameter_classname')dnl
popdef(`function_formal_name')dnl
')