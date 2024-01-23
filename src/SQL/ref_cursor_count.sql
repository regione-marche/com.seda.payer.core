CREATE OR REPLACE FUNCTION se00000.ref_cursor_count(storedname character varying)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
declare
	_storedName varchar(500);
	_inputVariable text;
	_arrayInput text[];
	_numeroRefCursor integer:=0;
begin
	_storedName:=substr(storedName,position('.' in storedName)+1,(position('(' in storedName)-position('.' in storedName))-1);
	begin
		select pg_catalog.pg_get_function_identity_arguments(p.oid)
		into strict _inputVariable
		FROM   pg_catalog.pg_proc p
		JOIN   pg_catalog.pg_namespace n ON n.oid = p.pronamespace
		WHERE  p.proname = _storedName                     -- function name
		-- AND n.nspname = 'public'                     -- schema name (optional)
		ORDER  BY 1;
	exception when NO_DATA_FOUND then
		raise exception 'Stored % non trovata',_storedName;
	end;
	_arrayInput:=regexp_split_to_array(_inputVariable,',');
	for i in 1..array_upper(_arrayInput,1) loop
		if (position('refcursor' in _arrayInput[i]))>0 then
			_numeroRefCursor:=_numeroRefCursor+1;
		end if;
	end loop;
	
	return _numeroRefCursor;
END ;
$function$
;