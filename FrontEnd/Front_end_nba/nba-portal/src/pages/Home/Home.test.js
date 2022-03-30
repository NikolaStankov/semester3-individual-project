import { render } from "@testing-library/react";
import Home from "./Home";

it("checkDivRender", () => {
  const { queryByTitle } = render(<Home />);
  const divHome = queryByTitle("homeText");
  expect(divHome).toBeTruthy();
});
