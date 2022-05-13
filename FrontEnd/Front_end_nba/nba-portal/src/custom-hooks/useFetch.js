import { useState, useEffect } from "react";

const useFetch = (url) => {
  const [data, setData] = useState(null);
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    var axios = require("axios").default;

    axios
      .get(url)
      .then((response) => {
        const responseData = response.data;
        setData(responseData);
        console.log(responseData);
        console.log(data);
      })
      .catch(function (error) {
        if (error.response) {
          setErrorMessage(error.response.data.message);
          console.log(errorMessage)
        }
      });
  }, [url]);

  return { data };
};

export default useFetch;
