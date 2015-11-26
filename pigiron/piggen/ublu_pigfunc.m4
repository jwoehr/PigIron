divert(-1)
changecom(`\\\\')
\\ pigfunc.m4
\\   Macros to autogenerate Java code for PigIron functions
\\   Copyright *C* 2008, 2015 Jack J. Woehr
\\   Part of the PigIron Project http://pigiron.sourceforge.net
\\   jwoehr@softwoehr.com
include(`pigsty.m4')

\\ Stream definitions
define(`null_stream',           `-1')dnl        \\ The bit bucket
define(`normal_stream',          `0')dnl        \\ Standard output
define(`file_header_stream',    `92')dnl        \\ Stream on which we define file header
define(`package_stream',        `93')dnl        \\ Stream on which we call out package
define(`imports_stream',        `94')dnl        \\ Stream on which we call out imports
define(`class_header_stream',   `95')dnl        \\ Stream on which we define class header
define(`constant_stream',       `97')dnl        \\ Stream on which we define static finals
define(`attribute_stream',      `98')dnl        \\ Stream on which we define attributes
define(`accessor_stream',      `100')dnl        \\ Stream on which we define accessors
define(`ctor_stream',          `110')dnl        \\ Stream on which we define ctor
define(`function_stream',      `120')dnl        \\ Stream on which we define functions
define(`compose_in_stream',    `130')dnl        \\ Stream on which we define composeInput
define(`compose_out_stream',   `135')dnl        \\ Stream on which we define composeInput
define(`get_function_name_stream', `139')dnl    \\ Stream on which we define getFunctionName()
define(`class_footer_stream',  `140')dnl        \\ Stream on which we define class footer
define(`file_footer_stream',   `150')dnl        \\ Stream on which we define file footer

\\ Set up our basic diversion
divert(0)dnl
push_divert(null_stream)dnl

\\ pigfunc_import(`import_name')
define(`pigfunc_import',`')

\\ pigfunc_class(`name',`extends',`package',`function_name',`comment')
define(`pigfunc_class',`dnl
define(`smapi_formal_function_name', $4)dnl \\ we need this later
push_divert(class_header_stream)dnl
pushdef(`x_name', $1)dnl
define(`myClassName',x_name())dnl
pushdef(`x_extends', $2)dnl
pushdef(`x_package', $3)dnl
pushdef(`x_function_name', $4)dnl
pushdef(`x_comment', $5)dnl
changecom(`/*')dnl
# myClassName().ublu
# perform SMAPI  x_function_name
# arguments to function:
# host is tuple variable with the instanced host object
ifelse(myClassName(),`CheckAuthentication', `', `# target_identifier == String `,' the target of the VSMAPI function
')dnl
changecom(`#')dnl
popdef(`x_comment')dnl
popdef(`x_function_name')dnl
popdef(`x_package')dnl
popdef(`x_extends')dnl
popdef(`x_name')dnl
pop_divert()dnl
')

\\ These two are used for the call args
define(`opt_ctor_param_list', `')
define(`opt_call_param_list', `')
 
\\ optional_ctor_params_comments(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_comments',`')

\\ optional_ctor_params_args(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_args',`dnl
ifelse(eval($# >= 3), 0, , eval($# == 3), 1, `,'  ` $2', `,' ` $2'optional_ctor_param_args(shift(shift(shift($@))))')')

\\ optional_ctor_param_instantiations(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_instantiations',`')

\\ pigfunc_ctors(OPTIONAL type, name, instance, t, n, i ..)
define(`pigfunc_ctors',`dnl
push_divert(class_header_stream)dnl
# paramarray is the tuple variable in which the SMAPI response is returned
pop_divert()dnl
push_divert(ctor_stream)dnl
FUNC myClassName `(' host`'ifelse(myClassName(),`CheckAuthentication', `', ` target_identifier')`'dnl
opt_ctor_param_list paramarray `)' `$'`'`['
        smapi -to @@paramarray smapi_formal_function_name @@host`'dnl
ifelse(myClassName(),`CheckAuthentication', `', ` @@target_identifier')`'dnl
opt_call_param_list
`]'`$'
pop_divert()dnl
')

\\ pigfunc_constant(`accessor', `type', `name', `initial_value', `comment')
define(`pigfunc_constant',`')

\\ pigfunc_attribute(`accessor', `scope', `type', `name', `initial_value', `set_accessor', `comment')
define(`pigfunc_attribute',`dnl
pushdef(`x_accessor', ifelse($1, `', `private ', $1 ))dnl
pushdef(`x_scope', ifelse($2, `', `', $2 ))dnl
pushdef(`x_type', $3)dnl
pushdef(`x_name', $4)dnl
pushdef(`x_initial_value', $5)dnl
pushdef(`x_set_accessor', ifelse($6, `', `public', $6))dnl
pushdef(`x_comment', $7)dnl
pushdef(`x_qualified_name', x_accessor()x_scope()x_type() x_name())dnl
pushdef(`x_get_accessor_name', ifelse(x_type, `boolean', `is_', `get_')`'x_name())dnl
push_divert(class_header_stream)dnl
changecom(`/*')dnl
# x_name == x_type `,' x_comment
changecom(`#')dnl
pop_divert()dnl
define(`opt_ctor_param_list', opt_ctor_param_list() x_name())dnl
define(`opt_call_param_list', opt_call_param_list() `@@'x_name())dnl
popdef(`x_get_accessor_name')dnl
popdef(`x_qualified_name')dnl
popdef(`x_comment')dnl
popdef(`x_set_accessor')dnl
popdef(`x_initial_value')dnl
popdef(`x_name')dnl
popdef(`x_type')dnl
popdef(`x_scope')dnl
popdef(`x_acessor')dnl
')

\\ javadoc_args(arg1, arg2, ... argn)
define(`javadoc_args',`')

\\ pigfunc_function(`accessor', `scope', `return_type', `return_init_expression', `name', `arguments', `throws' `comment', `function_body')
define(`pigfunc_function',`')

\\ Create the override of getFunctionName()
define(`pigfunc_get_function_name',`')

\\ Close a class definition
define(`pigfunc_endclass',`dnl
push_divert(class_footer_stream)dnl
# End
pop_divert()dnl
')

\\ Start definitions
define(`pigfunc_start',`dnl
push_divert(file_header_stream)dnl
include(`pigiron_copyright_comment.txt')
changecom(`/*')dnl
`# Autogenerated' date_string()`# by ublu_pigfunc.m4 Copyright *C* 2015 Jack J. Woehr'
`# Part of the PigIron Project http://pigiron.sourceforge.net'
changecom(`#')dnl
pop_divert()dnl
')

\\ pigfunc_compose_input_start()
define(`pigfunc_compose_input_start',`')

\\ pigfunc_compose_input_parm(type, value, formal_name)
define(`pigfunc_compose_input_parm',`')

\\ pigfunc_compose_optional_input_parm(type, value, formal_name)
define(`pigfunc_compose_optional_input_parm',`dnl
push_divert(`compose_in_stream')dnl
pushdef(`x_type', $1)dnl
pushdef(`x_value', $2)dnl
pushdef(`x_formal_name', $3)dnl
pushdef(`optional_formal_name',`optional_'x_formal_name)dnl
pigfunc_attribute(`public', `', `boolean', `optional_'x_formal_name, `true', `', `true means optional param will be used in VSMAPI function input')dnl
        if (`is_'optional_formal_name`(')) { // Only add if optional param is designated in use by `set_'optional_formal_name`'()
ifelse(x_type(),`CountedString',`dnl
            tempString = new VSMString`('x_value, "x_formal_name()");
            parameterArray.add`('new VSMInt4`('tempString.paramLength`('), "x_formal_name()_length"));
            parameterArray.add`('tempString);',`dnl
            parameterArray.add`('new x_type()`('x_value(), "x_formal_name()"`)';)
')
        }
popdef(`optional_formal_name')dnl
popdef(`x_formal_name')dnl
popdef(`x_value')dnl
popdef(`x_type')dnl
pop_divert()dnl
')

\\ pigfunc_compose_input_end()
define(`pigfunc_compose_input_end',`')

\\ pigfunc_compose_output_start()
define(`pigfunc_compose_output_start',`')

\\ Recognize that a type ends in the string `Array' and treat
\\ it as an Array type in output composition, use VSMArray.modelArray() .
\\ Returns -1 if not a match.
define(`is_type_named_array',`regexp(`$1',`Array$')')

\\ pigfunc_compose_output_parm(type, value, formal_name)
define(`pigfunc_compose_output_parm',`')

\\ pigfunc_compose_output_end()
define(`pigfunc_compose_output_end',`')

\\ End definitions
define(`pigfunc_end',`dnl
push_divert(normal_stream)dnl
undivert(file_header_stream)dnl
undivert(package_stream)dnl
undivert(imports_stream)dnl
undivert(class_header_stream)dnl
undivert(constant_stream)dnl
undivert(ctor_stream)dnl
undivert(attribute_stream)dnl
undivert(accessor_stream)dnl
undivert(compose_in_stream)dnl
undivert(compose_out_stream)dnl
undivert(get_function_name_stream)dnl
undivert(function_stream)dnl
undivert(class_footer_stream)dnl
undivert(file_footer_stream)dnl
pop_divert()dnl
')

\\ End of pigfunc.m4
pop_divert()dnl
