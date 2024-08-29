import { test, expect } from "@playwright/test";

test.describe("Success Story", async () => {
  test("Get User With Valid Token", async ({ request }) => {
    const response = await request.get("https://api.github.com/user", {
      headers: {
        Authorization: "Bearer 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",
        "Content-Type": "application/json",
      },
    });

    expect(response.ok()).toBeTruthy();
    const data = await response.json();
    console.log("data", data);
  });

  test("Update User Bio With Valid Token", async ({ request }) => {
    const response = await request.patch("https://api.github.com/user", {
      headers: {
        Authorization: "Bearer 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",
        "Content-Type": "application/json",
      },
      body: { bio: "Your new bio content here." },
    });

    expect(response.ok()).toBeTruthy();
    const data = await response.json();
    console.log("data", data);
  });
});

test.describe("Failure Test cases", async () => {
  test("No Token Provided", async ({ request }) => {
    const response = await request.get("https://api.github.com/user", {
      headers: {
        "Content-Type": "application/json",
      },
    });

    expect(response.status()).toEqual(401);
    const data = await response.json();
    console.log("data", data);
  });

  test("Invalid Token Provided", async ({ request }) => {
    const response = await request.get("https://api.github.com/user", {
      headers: {
        Authorization: "ABCDE123456",

        "Content-Type": "application/json",
      },
    });

    expect(response.status()).toEqual(401);
    const data = await response.json();
    console.log("data", data);
  });
  test("Forbidden Access (Token Without Necessary Permissions)", async ({
    request,
  }) => {
    const response = await request.get("https://api.github.com/user", {


      headers: {
        Authorization: "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",

        "Content-Type": "application/json",
      },
    });

    expect(response.status()).toEqual(403);
    const data = await response.json();
    console.log("data", data);
  });
});