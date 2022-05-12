import { useState, useEffect } from "react";

const usePostUnauthenticated = (url, request) => {
  const [response, setResponse] = useState(null);

  useEffect(() => {
    var axios = require("axios").default;

    axios.post(url, request).then((response) => {
      setResponse(response);
    });
  }, [url]);

  return { response };
};

export default usePostUnauthenticated;
