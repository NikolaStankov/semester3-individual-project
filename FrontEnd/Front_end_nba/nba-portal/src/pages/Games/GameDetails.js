import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import useFetch from "../../custom-hooks/useFetch";

const GameDetails = () => {
  let { id } = useParams();
  const url = "http://localhost:8080/games/" + id;

  const { data: game } = useFetch(url);

  return (
    <>
      <div>Game details - id:{id}</div>
    </>
  );
};

export default GameDetails;
