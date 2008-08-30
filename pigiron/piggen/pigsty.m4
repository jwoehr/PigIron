changecom(`\\\\')
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
\\ upcase first char
define(`upcase_initial',`upcase(substr(`$1',0,1))`'substr(`$1',1)')
\\ lowcase first char
define(`lowcase_initial',`lowcase(substr(`$1',0,1))`'substr(`$1',1)')

\\ Convert an xcc_xcc.. (etc.) to XccXcc...
define(`javaize_regexp',`^\(\w*\)_\(.*\)')
define(`javaize',`ifelse(regexp(`$1', javaize_regexp), `-1', upcase_initial(`$1'), `regexp($1, javaize_regexp, `javaize(`\1')`'upcase_initial(`\2')')')')
\\ Convert an xcc_xcc.. (etc.) to xccXcc...
define(`javaize_lc',`lowcase_initial(javaize(`$1'))')

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
