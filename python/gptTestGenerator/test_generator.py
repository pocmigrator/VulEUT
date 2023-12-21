import openai
import os

os.environ["http_proxy"] = "http://localhost:7890"
os.environ["https_proxy"] = "http://localhost:7890"

proxies = {'http': "http://127.0.0.1:7890",
          'https': "http://127.0.0.1:7890"}
openai.proxy = proxies
openai.api_key = "sk-xxxx"
MODEL = "gpt-3.5-turbo"
systemContent = "You are a java testing expert, and now you need to write usable unit tests on your client project to " \
                "test whether the client project can communicate the vulnerability input from the function entry to the vulnerability code"

template = ""
prompts_directory_path = ""
output_directory_path = ""

def requestLLMToGenerateUnitTest(requestContent: str):
    try:
        response = openai.ChatCompletion.create(
            model=MODEL,
            messages=[
                {"role": "system", "content": systemContent},
                {"role": "user", "content": template},
                {"role": "user", "content": requestContent},
            ],
            temperature=1,
        )
        responseContent = response['choices'][0]['message']['content']

        return responseContent.strip().splitlines()
    except Exception as e:
        print("error", e)

def writeUnitTestToFile(lines: str, outputFilepath:str):
    try:
        line_start_index = 0
        line_end_index = 0
        index = 0
        for line in lines:
            index = index + 1
            if line == "```java":
                line_start_index = index
            if line == "```":
                line_end_index = index
                break
        java_code = lines[line_start_index: line_end_index - 1]

        with open(outputFilepath, "w") as java_file:
            for line in java_code:
                java_file.write(line + "\n")

        print("finished")
    except Exception as e:
        print("error", e)


def readPrompts():
    file_prompts = []
    if os.path.exists(prompts_directory_path) and os.path.isdir(prompts_directory_path):
        for filename in os.listdir(prompts_directory_path):
            file_path = os.path.join(prompts_directory_path, filename)
            if os.path.isfile(file_path) and filename.endswith('.txt'):
                with open(file_path, 'r') as file:
                    content = file.read()
                    file_prompts.append(content)
    else:
        print(f"dir '{prompts_directory_path}' not exist")

    return file_prompts


if __name__ == '__main__':
    file_prompts = readPrompts()

    for idx, prompt in enumerate(file_prompts, start=1):
        # print("prompt:")
        # print(prompt)

        outputFilepath = output_directory_path + "/" + "output-" + str(idx) + ".txt"

        responseLines = requestLLMToGenerateUnitTest(prompt)

        writeUnitTestToFile(responseLines, outputFilepath)
