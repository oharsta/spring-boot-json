CREATE OPERATOR #-#(
  PROCEDURE = jsonb_exists,
  LEFTARG = JSONB,
  RIGHTARG = TEXT,
  RESTRICT = contsel,
  JOIN = contjoinsel);