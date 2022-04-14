import React, { useEffect } from "react";

const Home = () => {
  useEffect(() => {
    console.log(Date.now());
  }, []);

  return <div title="homeText">HOME</div>;
};

export default Home;
