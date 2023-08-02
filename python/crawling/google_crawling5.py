from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.keys import Keys 
from selenium.webdriver.common.by import By 
import urllib.request
from urllib.parse import quote_plus
from bs4 import BeautifulSoup
from selenium import webdriver
import time
import os 
from multiprocessing import Pool 
import pandas as pd 

key=pd.read_csv('./keyword.txt', names=['keyword'])
keyword=[] 
[keyword.append(key['keyword'][x]) for x in range(len(key))]
print(key)

def scroll_down():
    global driver
    last_height = driver.execute_script("return document.body.scrollHeight")

    while True:
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        time.sleep(5)
        new_height = driver.execute_script("return document.body.scrollHeight")

        if new_height == last_height:
            time.sleep(5)
            new_height = driver.execute_script("return document.body.scrollHeight")

            try:
                driver.find_element(By.CLASS_NAME,"mye4qd").click()   # Q4LuWd mye4qd
            except:
               if new_height == last_height:
                   break

        last_height = new_height

def set_chrome_driver():
    global driver
    driver = webdriver.Chrome()
    driver.implicitly_wait(3)
    return driver


def createFolder(directory): 
    try: 
        if not os.path.exists(directory): 
            os.makedirs(directory) 
    except OSError: 
        print ('Error: Creating directory. ' + directory) 

def image_download(keyword): 
    createFolder('./'+keyword+'_high resolution')
    driver = set_chrome_driver()
    driver.implicitly_wait(3)  
    
    print(keyword, '검색')
    
    driver.get("https://www.google.co.kr/imghp?hl=ko&tab=wi&authuser=0&ogbl")
    Keyword = driver.find_element(By.NAME, "q")  # find_element_by_name
    Keyword.send_keys(keyword) 
    Keyword.send_keys(Keys.RETURN)
    
    time.sleep(2)

    print(keyword+' 스크롤 중 .............') 
    
    scroll_down()

    html = driver.page_source
    
    driver.close() 
    
    #print("html " + html)

    soup = BeautifulSoup(html, 'html.parser')
    images = soup.find_all('img', attrs={'class':'rg_i Q4LuWd'})

    #print("images " + images)

    print('number of img tags: ', len(images))
    
    n = 1
    for i in images:

        try:
            imgUrl = i["src"]
        except:
            imgUrl = i["data-src"]

        with urllib.request.urlopen(imgUrl) as f:
            with open( "./"+keyword+"_high resolution/" + keyword + str(n) + '.jpg', 'wb') as h:
                img = f.read()
                h.write(img)
        n += 1
        
    print(keyword+' ---다운로드 완료---') 

# ============================================================================= 
# 실행 
# ============================================================================= 

if __name__=='__main__': 
    pool = Pool(processes=4) # 4개의 프로세스를 사용합니다. 
    pool.map(image_download, keyword)

