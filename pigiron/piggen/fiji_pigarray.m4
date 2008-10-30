divert(-1)
changecom(`\\\\')
\\ pigarray.m4
\\   Macros to autogenerate Java code for PigIron Array parameters
\\   Copyright *C* 1999, 2001, 2008 Jack J. Woehr
\\   Part of the PigIron Project http://pigiron.sourceforge.net
\\   jwoehr@softwoehr.com
include(`pigsty.m4')

\\ Stream definitions
define(`null_stream',         `-1')dnl        \\ The bit bucket
define(`normal_stream',        `0')dnl        \\ Standard output
define(`file_header_stream',  `92')dnl        \\ Stream on which we define file header
define(`package_stream',      `93')dnl        \\ Stream on which we call out package
define(`imports_stream',      `94')dnl        \\ Stream on which we call out imports
define(`class_header_stream', `95')dnl        \\ Stream on which we define class header
define(`constant_stream',     `97')dnl        \\ Stream on which we define static finals
define(`attribute_stream',    `98')dnl        \\ Stream on which we define attributes
define(`accessor_stream',    `100')dnl        \\ Stream on which we define accessors
define(`ctor_stream',        `110')dnl	      \\ Stream on which we define the ctors
define(`function_stream',    `120')dnl        \\ Stream on which we define functions
define(`model_stream',       `130')dnl	      \\ Stream on which we define modelFormalParameters
define(`class_footer_stream',`140')dnl        \\ Stream on which we define class footer
define(`file_footer_stream', `150')dnl        \\ Stream on which we define file footer

\\ Set up our basic diversion
divert(0)dnl
push_divert(null_stream)dnl

\\ pigparm_import(`import_name')
define(`pigparm_import',`')

\\ pigparm_class(`name',`extends',`package',`comment')
define(`pigparm_class',`dnl
push_divert(class_header_stream)dnl
pushdef(`x_name', $1)dnl
pushdef(`x_extends', $2)dnl
pushdef(`x_package', $3)dnl
pushdef(`x_comment', $4)dnl
define(`myClass',x_name())dnl
define(`mySuperClass',x_extends())dnl
\ x_comment() \
popdef(`x_comment')dnl
popdef(`x_package')dnl
popdef(`x_extends')dnl
popdef(`x_name')dnl
pop_divert()dnl
')

\\ pigparm_constant(`accessor', `type', `name', `initial_value', `comment')
define(`pigparm_constant',`dnl
push_divert(constant_stream)dnl
    \ (java $2) param_entity_name().$3 $5 \
    value param_entity_name().$3
    $4 to param_entity_name().$3
pop_divert()dnl
')

\\ pigparm_attribute(`accessor', `scope', `type', `name', `initial_value', `set_accessor', `comment'
define(`pigparm_attribute',`')

\\ pigparm_ctors
define(`pigparm_ctors',`')

\\ pigparm_model_start()
define(`pigparm_model_start',`')

\\ pigparm_model_parm(type, value, formal_name)
define(`pigparm_model_parm',`')

\\ pigparm_model_end()
define(`pigparm_model_end',`')

\\ pigparm_function(`accessor', `scope', `return_type', `return_init_expression', `name', `arguments', `comment', `function_body')
define(`pigparm_function',`')

\\ pigparm_endclass ... Close a class definition
define(`pigparm_endclass',`')
push_divert(file_footer_stream)dnl

\ End of autogenerated code \

pop_divert()dnl
')

\\ Start definitions
define(`pigparm_start',`dnl
push_divert(file_header_stream)dnl
`\ Autogenerated date_string()by fiji_pigarray.m4 \'
`\ Copyright *C* 2008 Jack J. Woehr \'
`\ Part of the PigIron Project http://pigiron.sourceforge.net \'
pop_divert()dnl
')

\\ End definitions
define(`pigparm_end',`dnl
push_divert(normal_stream)dnl
undivert(file_header_stream)dnl
undivert(package_stream)dnl
undivert(imports_stream)dnl
undivert(class_header_stream)dnl
undivert(constant_stream)dnl
undivert(attribute_stream)dnl
undivert(accessor_stream)dnl
undivert(model_stream)dnl
undivert(ctor_stream)dnl
undivert(function_stream)dnl
undivert(class_footer_stream)dnl
undivert(file_footer_stream)dnl
pop_divert()dnl
')

\\ End of pigarray.m4
pop_divert()dnl
