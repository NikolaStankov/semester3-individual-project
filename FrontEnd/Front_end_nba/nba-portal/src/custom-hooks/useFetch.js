import { useState, useEffect } from "react";

const useFetch = (url) => {
  const [data, setData] = useState(null);

  useEffect(() => {
    var axios = require("axios").default;

    axios.get(url).then((response) => {
      const responseData = response.data || [];
      setData(responseData);
      console.log(responseData);
      console.log(data);
    });
  }, [url]);

  return { data };
};

export default useFetch;
