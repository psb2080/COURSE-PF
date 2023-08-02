# -*- coding: utf-8 -*-
"""
  Created on Tue Apr 11 11:20:04 2023
  
  @PC user: 505-20
  
  FileName : ShootingGame.py
  Author   : TEAM STEP
  Description : 슈팅 게임
"""
"""
    pip install tk     
    pip install pymysql
    
    랭킹연동 : 마리아 DB 깔아야 됨

    # 데이터베이스 생성
    CREATE DATABASE pythonDB;

    # 테이블 생성
    DROP TABLE IF EXISTS GameRankingTbl ;
    
    CREATE TABLE GameRankingTbl 
    (
        GR_NO    INT(11) NOT NULL AUTO_INCREMENT,
        GR_NAME  VARCHAR(100) NOT NULL DEFAULT '' COLLATE 'utf8mb4_general_ci',
        GR_SCORE INT(11) NULL DEFAULT NULL,
        GR_DATE  DATETIME NULL DEFAULT current_timestamp(),
        PRIMARY KEY (`GR_NO`) USING BTREE
    )
    COMMENT='슈팅게임 게임순위 테이블'
    COLLATE='utf8mb4_general_ci'
    ENGINE=InnoDB
    ;

"""

"""* 선언부 *"""

# import 선언
import time
import pygame
import sys
import random
from time import sleep
from tkinter import *
#import pymysql
from tkinter import messagebox

# 게임시작시 게임 초기화, 전역변수 선언
def initGame( ) :
  global BLACK, padWidth, padHeight, background_height, rockImage, explosionSound, screen, gamePad, start_time
  global background, background2, fighter, missile, explosion, missileSound, gameOverSound, clock, shotCount, background_speed
  global missileXY, gaugeWidth, gaugeHeight, gaugeX, gaugeY, gaugeValue, rock, rockSize, rockWidth, rockHeight, rock1, rockSize1, dy ,rocks, rock, rockA, nuclearMissile, nuclearSound, redping
  global rockX, rockX1, rockY, rockSpeed, dx, rockAngle, rockDirection, fighterSize, fighterWidth, fighterHeight, x, y, fighterX, fighterY, fighterA
  
  pygame.init( )
  
  BLACK = ( 0, 0, 0 )
  padWidth = 480  # 게임화면의 가로크기
  padHeight = 640  # 게임화면의 세로크기
  background_height = padHeight  # 움직이는 배경 변수 선언 
  rockImage = [ './images/rock01.png', './images/rock02.png', './images/rock03.png', './images/rock04.png', './images/rock05.png', \
                './images/rock06.png', './images/rock07.png', './images/rock08.png', './images/rock09.png', './images/rock10.png', \
                './images/rock11.png', './images/rock12.png', './images/rock13.png', './images/rock14.png', './images/rock15.png', \
                './images/rock16.png', './images/rock17.png', './images/rock18.png', './images/rock19.png', './images/rock20.png', \
                './images/rock21.png', './images/rock22.png', './images/rock23.png', './images/rock24.png', './images/rock25.png', \
                './images/rock26.png', './images/rock27.png', './images/rock28.png', './images/rock29.png', './images/rock30.png' ]
  explosionSound = [ './images/explosion01.wav', './images/explosion02.wav', './images/explosion03.wav', './images/explosion04.wav']
  screen = pygame.display.set_mode((padWidth, padHeight), pygame.DOUBLEBUF)
  start_time = 0   # 게임 시작시간 초기화
  gamePad = pygame.display.set_mode( ( padWidth, padHeight ) )
  
  pygame.display.set_caption( 'PyShooting' )  # 게임 이름
  background = pygame.image.load( './images/background1.png' )  # 배경 그림 1, 2, 3
  background2 = background.copy( )  # 움직이는 배경위한 소스추가
  fighter = pygame.image.load( './images/fighter-001.png' ) # 전투기 그림
  missile = pygame.image.load( './images/missile22.png' )  # 미사일 그림
  explosion = pygame.image.load( './images/explosion.png' )  # 폭발 그림
  pygame.mixer.music.load( './images/music.wav' )  # 배경음악
  pygame.mixer.music.play( -1 )  # 배경음악 재생
  missileSound = pygame.mixer.Sound( './images/missile.wav' )  # 미사일 사운드
  gameOverSound = pygame.mixer.Sound( './images/gameover.wav' )  # 게임 오버 사운드
  clock = pygame.time.Clock( )
  shotCount = 0   # 점수 초기화
  background_speed = 0.2   # 배경 이동속도 
  #필살기
  nuclearMissile = pygame.image.load( './images/NM_300x300.png')
  nuclearSound = pygame.mixer.Sound('nuclear2.wav')
  redping = pygame.image.load('./images/redLight_300x300.png')
  
  missileXY = []  # 무기 좌표 리스트
  fighterA = fighter.get_rect(centerx = padWidth // 2, bottom = padHeight)
  # 게이지 표시
  gaugeWidth = 100
  gaugeHeight = 10
  gaugeX = padWidth / 2 - gaugeWidth / 2
  gaugeY = padHeight - gaugeHeight - 10
  gaugeValue = 0
  # 운석 랜덤 생성
  rock = pygame.image.load( random.choice( rockImage ) ) 
  rocks = []
  rockfreq = 5 # 운석 떨어지는 빈도수 점수에 따라 수정
  rockappear = random.randint( 2, rockfreq ) 
  for i in range( rockappear ) :
     rockA = rock.get_rect(left=random.randint( 0, padWidth - rock.get_width( ) ), top = -30 )
     dy = random.randint( 1, rockfreq )
     rocks.append((rockA, dy))
  rockWidth = 4
  rockHeight = 10
  dx = random.randint(1,2) # 운석 좌우 움직임
  # 전투기 초기 위치( x, y )
  x = padWidth * 0.45
  y = padHeight * 0.9
  fighterX = 0
  fighterY = 0
  
  # 보스 
  global boss, bossA, bossX, bossY ,tmpX, tmpY 
 
  # 보스 좌표 이동   
  boss = [
           pygame.image.load( './images/Boss0.png' ), 
           pygame.image.load( './images/Boss1.png' ),
           pygame.image.load( './images/Boss2.png' ),
           pygame.image.load( './images/Boss3.png' )  
         ]  
  bossX = padWidth * 0.45
  bossY = padHeight * 0.1
  tmpX = 1    # 보스 X 이동 폭
  tmpY = 1    # 보스 y 이동 폭
  
"""* 게임플레이 구현부 *"""

# 첫 화면( 시놉시스 ) 구현
def runStory( ) :
  global padWidth, padHeight, background
  
  
  drawObject( background, 0, 0 )  # 배경 화면 그리기
  
  # 한국 글씨 폰트 적용
  font_path = './font/12Bold.ttf'
  font_size = 40
  korean_font = pygame.font.Font(font_path, font_size)
  
  
  # 시놉시스
  text_lines = ["미래의 지구,", "외계인의 침공이", "시작되었다.", "외계인은", "이미 지구 내에", "깊숙이 침투했고", "전 인류는 ", "모든 기술력을 모아 ", "‘STEP-1호기’를 ", "완성하고 마는데...", "‘STEP-1호기’로 ", "지구 안에 침투한 ", "외계인들을 ", "모두 물리치자."]
  text_positions = [(padWidth // 2, padHeight + korean_font.get_height() * i) for i in range(len(text_lines))]
  text_speed = 1.3
  
  # 프레임 조정을 위한 시간 추가
  clock = pygame.time.Clock()
  
  # 키를 받아 종료하기 전까지 글씨 올라감
  while True:
      # 끄면 종료됨
      for event in pygame.event.get():
          if event.type == pygame.QUIT:
              pygame.quit()
              quit()
              
      # 배경을 우주 이미지로 띄워줌
      drawObject( background, 0, 0 )
  
      # 글씨의 위치 지정
      for i, position in enumerate(text_positions):
          x, y = position
          y -= text_speed
          text_positions[i] = (x, y)
  
      # 화면에 글씨 표현
      for i, line in enumerate(text_lines):
          text = korean_font.render(line, True, (255, 255, 255))
          x, y = text_positions[i]
          screen.blit(text, (x - text.get_width() // 2, y))
  
      # 화면 업데이트
      pygame.display.update()
  
      # 화면 프레임 정해주기
      clock.tick(90)
      
      # 아무키나 누르면 runMenu( ) 호출
      for event in pygame.event.get( ) :
        if event.type in [ pygame.QUIT ] : 
          pygame.quit( )
          sys.exit( )
        if event.type == pygame.KEYDOWN :  
          runMenu( )
          
# 시작화면 출력
def runMenu( ) :
    global padWidth, padHeight, background, background2
    
    
    background = pygame.image.load( './images/background1.png' )
    background2 = pygame.image.load( './images/background1.png' )
    drawObject( background, 0, 0 )  # 배경 화면 그리기
    text1 = 'press any key'
    text2 = '* k : keyboard mode '
    text3 = '* m : mouse mode '
    textfont = pygame.font.Font( './images/NanumGothic.ttf', 40 )
    text1 = textfont.render( text1, True, ( 255, 255, 255 ) )
    text2 = textfont.render( text2, True, ( 255, 255, 255 ) )
    text3 = textfont.render( text3, True, ( 255, 255, 255 ) )
    textpos1 = text1.get_rect( )
    textpos1.center = ( padWidth / 2, padHeight / 2 - 50 )
    textpos2 = text2.get_rect( )
    textpos2.center = ( padWidth / 2, padHeight / 2  )
    textpos3 = text3.get_rect( )
    textpos3.center = ( padWidth / 2, padHeight / 2 + 50 )
    gamePad.blit( text1, textpos1 )
    gamePad.blit( text2, textpos2 )
    gamePad.blit( text3, textpos3 )
    pygame.display.update( )
    while True :
      for event in pygame.event.get( ) :
        if event.type in [ pygame.QUIT ] :  # 게임 프로그램 종료
          pygame.quit( )
          sys.exit( )
        if event.type == pygame.KEYDOWN :
          
          if event.key == pygame.K_k : # k 누르면 키보드 모드
            runGame_keyboard( )
          if event.key == pygame.K_m : # m 누르면 마우스 모드
            runGame_mouse( )
          else:                        # 다른키 누르면 default( 키보드 모드 )로 시작
            runGame_keyboard( )

# 게임 플레이 구현
def runGame_keyboard( ) :
  global BLACK, padWidth, padHeight, background_height, explosionSound 
  global background, background2, fighter, missile, explosion, shotCount, background_speed, nuclearMissile, nuclearSound, redping
  global gaugeWidth, gaugeHeight, gaugeX, gaugeValue, rocks 
  global x, y, fighterX, fighterY, rock, dx, rockfreq  
  global mouse_x, mouse_y  # 보스용
  
  # 움직이는 배경이미지을 위한 변수 선언
  # 화면은 Top, Left 좌표는 (0,0)로 background2_y 는 0 아래쪽 마이너스 좌표에 위치해야 함
  background_y  = 0
  background2_y = -background_height
  
  destroySound = pygame.mixer.Sound( random.choice( explosionSound ) )  # 파괴될 때마다 다른 사운드 선택
  
  # 핵미사일
  nuclearMissile_size = nuclearMissile.get_rect().size
  nuclearMissile_Width = nuclearMissile_size[0]
  nuclears = []
  nuclearMissile_speed = -10
  
  # 빨간점
  redping_size = redping.get_rect().size
  redping_Width = redping_size[0]
  redpings = []
  redping_speed = 0
  redping_shown_time = 0
  
  #start_time = time.time() # 게임 시간 계산을 위해 시작시간 저장
  
  onGame = False
  shotCount = 0
  rockPassed = 0
  while not onGame :
    for event in pygame.event.get( ) :
      if event.type in [ pygame.QUIT ] :  # 게임 프로그램 종료
        pygame.quit( )
        sys.exit( )
        
      if event.type == pygame.KEYDOWN :
        if event.key == pygame.K_LEFT :  # 전투기를 왼쪽으로 이동
          fighterX -= 5
        if event.key == pygame.K_RIGHT :  # 전투기를 오른쪽으로 이동
          fighterX += 5
        if event.key == pygame.K_UP : # 위로 이동
          fighterY -= 5
        if event.key == pygame.K_DOWN : # 아래로 이동
          fighterY += 5
        if event.key == pygame.K_SPACE :  # 미사일 발사
            missileSound.play( )  # 미사일 사운드 재생
            missileA = missile.get_rect(centerx=x+fighterA.width/2, top=y)
            missileXY.append(missileA)
        if event.key == pygame.K_q:   # q 눌렀을 때 모든 운석 없애기
          if gaugeValue == 100 :
            nuclearSound.play()
            
            redping_x_pos = (padWidth / 2) - (redping_Width / 2)
            redping_y_pos = 150 #padHeight - skillEffect_Height
            redpings.append([redping_x_pos, redping_y_pos])
            redping_shown_time = pygame.time.get_ticks()
            gaugeValue = 0

          else :
            gaugeValue = gaugeValue
      
      # 대각선 방향 이동 추가
      elif event.type == pygame.KEYUP :
        if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT :
          fighterX = 0
        elif event.key == pygame.K_UP or event.key == pygame.K_DOWN :
          fighterY = 0

     # 빨간점 지속시간 설정, 빨간점 끝난 후 핵미사일 발사 
    if redping_shown_time != 0 and pygame.time.get_ticks() - redping_shown_time >= 1000:
       redpings = []
       
       nuclearMissile_x_pos = (padWidth / 2) - (nuclearMissile_Width / 2)
       nuclearMissile_y_pos = 30 #padHeight - skillEffect_Height
       nuclears.append([nuclearMissile_x_pos, nuclearMissile_y_pos])
       destroySound.play()
       
       shotCount += len(rocks)
       rocks = []
       rockappear = random.randint(2, 5)
       for i in range (rockappear) :
          rockA = rock.get_rect(left=random.randint(0, padWidth - rock.get_width()), top=-30)
          dy = random.randint(1, 2)
          rocks.append((rockA, dy))
       redping_shown_time = 0
       
    # 움직이는 배경 화면 좌표값 변경 
    background_y  += background_speed
    background2_y += background_speed
    
    #ingame에서 운석 수 변경 
    if shotCount > 15 :
        rockfreq = 7
    
    if ( background_y == background_height ) :
        background_y =  -background_height
        
    if ( background2_y == background_height ) :
        background2_y = -background_height
      
    # 배경 화면 그리기        
    #drawObject( background, 0, 0 )  
    drawObject( background,  0, background_y )
    drawObject( background2, 0, background2_y )     
    """
    #게임시간이 3초가 되면 다음 화면으로 넘어감( 일단 중간에 왕 나오는 배경 3초씩 )
    gametime = int(time.time()-start_time)
    if gametime > 3 and gametime < 7 :
      background = pygame.image.load( './images/background0.png' )
      background2 = background.copy()
    elif gametime > 6 and gametime < 10 :
      background = pygame.image.load( './images/background2.png' )
      background2 = background.copy()
    elif gametime > 9 and gametime < 13 :
      background = pygame.image.load( './images/background0.png' )
      background2 = background.copy()
    elif gametime > 12 and gametime < 16 :
      background = pygame.image.load( './images/background3.png' )
      background2 = background.copy()
   """   
   # 비행기를 게임 화면의 ( x, y ) 좌표에 그리기
    x += fighterX
    if x < 0 :
      x = 0
    elif x > padWidth - 50:
      x = padWidth - 50
    
    y += fighterY
    if y < 0 :
      y = 0
    elif y > padHeight-30:
      y = padHeight-30
      
    #운석 그리기
    

    for rockA, dy in rocks:
        rockA.top += dy * 0.7 #빠른 것 같아 느리게 
        rockA.left += dx * 0.7 # 운석 x축 움직임 추
        if rockA.top > padHeight:
            rocks.remove((rockA,dy)) # 운석 하나 떨어지면 게임오버 수정
            rockGen()
            rockPassed += 1
            
        if rockPassed == 5 : # 운석 5개 놓치면 게임오버
            gameOver( )
            
        for missileA in missileXY:
            missileA.top -= 5
            if missileA.top < 0:
                missileXY.remove(missileA)
    # 핵미사일 그리기
    nuclears = [ [w[0], w[1] - nuclearMissile_speed] for w in nuclears ]
    
    for nuclearMissile_x_pos, nuclearMissile_y_pos in nuclears:
        screen.blit(nuclearMissile, (nuclearMissile_x_pos, nuclearMissile_y_pos))
    
    # 빨간점 그리기
    redpings = [ [w[0], w[1] - redping_speed] for w in redpings ]
    
    for redping_x_pos, redping_y_pos in redpings:
        screen.blit(redping, (redping_x_pos, redping_y_pos))
        
    # 미사일 발사 화면에 그리기
    for rockA, dy in rocks:
        for missileA in missileXY:
            if missileA.colliderect(rockA):
                    missileXY.remove(missileA)
                    gamePad.blit(explosion, (rockA.left, rockA.top))
                    rocks.remove((rockA, dy))
                    rockGen()
                    shotCount += 1
                    destroySound.play()
                    gaugeValue += 10
                    if gaugeValue > 100 :
                        gaugeValue = 100
  
    for rockA, dy in rocks:
        if rockA.colliderect(fighter.get_rect(left=x,top=y)):
            gameOver()
        
    for rockA, dy in rocks:
      gamePad.blit(rock, rockA)
    
      if rockA.left < 0 : # 운석 좌우 움직임 제한 
          dx = -dx
          #생성된 rock이 화면 오른쪽에 부딪힐 때
      elif rockA.left > ( padWidth - rock.get_width() ) :
          rockA.left = padWidth - rock.get_width() 
          dx = -dx
      
    for missileA in missileXY:
      gamePad.blit(missile, missileA)

    drawObject(fighter, x, y)
    
    destroySound = pygame.mixer.Sound( random.choice( explosionSound ) )
    writeScore( shotCount )
      
    # 놓친 운석 수 표시
    writePassed( rockPassed )
    
    #운석 그리기
    pygame.draw.rect(gamePad, (255, 0, 0), (gaugeX, gaugeY, gaugeWidth, gaugeHeight), border_radius=3)  # Red gauge bar
    
    pygame.draw.rect(gamePad, (0, 255, 0), (gaugeX, gaugeY, gaugeWidth * (gaugeValue / 100), gaugeHeight), border_radius=50)  # Green gauge bar based on gaugeValue
    useSkill(gaugeValue) 
    #pygame.display.update( )  # 게임 화면을 다시 그림
    
    # 보스를 게임 화면의 ( x, y ) 좌표에 그리기  
    if ( shotCount > 20 and shotCount < 40 ) :  
        shwoBoss( "keyboard", 0, 1.0 )               
    elif ( shotCount > 60 and shotCount < 80 ) :   
        shwoBoss( "keyboard", 1, 1.3 )  
    elif ( shotCount > 100  and shotCount < 120 ) :
        shwoBoss( "keyboard", 2, 1.6 )
    elif ( shotCount > 140 )  :
        shwoBoss( "keyboard", 3, 1.9 )

    pygame.display.update( )  # 게임 화면을 다시 그림
    
    gamePad.fill( BLACK )  # 게임 화면 ( 검은색 )
    
    clock.tick( 90 )  # 게임화면의 초당 프레임수를 60으로 설정
    
  pygame.quit( )  # pygame 종료
  
# 마우스 플레이 구현
def runGame_mouse( ) :
  global BLACK, padWidth, padHeight, background_height, explosionSound 
  global background, background2, fighter, missile, explosion, shotCount, background_speed, nuclearMissile, nuclearSound, redping
  global gaugeWidth, gaugeHeight, gaugeX, gaugeValue, rocks 
  global x, y, fighterX, fighterY, rock, dx, rockfreq  
  global mouse_x, mouse_y  # 보스용
  
  # 움직이는 배경이미지을 위한 변수 선언
  # 화면은 Top, Left 좌표는 (0,0)로 background2_y 는 0 아래쪽 마이너스 좌표에 위치해야 함
  background_y  = 0
  background2_y = -background_height
  
  # 핵미사일
  nuclearMissile_size = nuclearMissile.get_rect().size
  nuclearMissile_Width = nuclearMissile_size[0]
  nuclears = []
  nuclearMissile_speed = -10
  
  # 빨간점
  redping_size = redping.get_rect().size
  redping_Width = redping_size[0]
  redpings = []
  redping_speed = 0
  redping_shown_time = 0
  
  destroySound = pygame.mixer.Sound( random.choice( explosionSound ) )  # 파괴될 때마다 다른 사운드 선택
  
  onGame = False
  shotCount = 0
  rockPassed = 0
  mouse_x=padWidth/2
  mouse_y=620

  while not onGame :
    for event in pygame.event.get( ) :
      if event.type in [ pygame.QUIT ] :  # 게임 프로그램 종료
        pygame.quit( )
        sys.exit( )
        
      if event.type == pygame.MOUSEMOTION:
          mouse_x = event.pos[0]
          mouse_y = event.pos[1]

          
      if event.type == pygame.MOUSEBUTTONDOWN :
          if event.button == 1 :
              missileSound.play( )  # 미사일 사운드 재생
              missileA = missile.get_rect(centerx=mouse_x, top=mouse_y)
              missileXY.append(missileA)
          elif event.button == 3 :
            if gaugeValue == 100 :
              
              redping_x_pos = (padWidth / 2) - (redping_Width / 2)
              redping_y_pos = 150 
              redpings.append([redping_x_pos, redping_y_pos])
              redping_shown_time = pygame.time.get_ticks()
              gaugeValue = 0


     # 빨간점 지속시간 설정, 빨간점 끝난 후 핵미사일 발사 
    if redping_shown_time != 0 and pygame.time.get_ticks() - redping_shown_time >= 1000:
         redpings = []
         
         nuclearMissile_x_pos = (padWidth / 2) - (nuclearMissile_Width / 2)
         nuclearMissile_y_pos = 30 #padHeight - skillEffect_Height
         nuclears.append([nuclearMissile_x_pos, nuclearMissile_y_pos])
         destroySound.play()
         
         shotCount += len(rocks)
         rocks = []
         rockappear = random.randint(2, 5)
         for i in range (rockappear) :
            rockA = rock.get_rect(left=random.randint(0, padWidth - rock.get_width()), top=-30)
            dy = random.randint(1, 2)
            rocks.append((rockA, dy))
         gaugeValue = 0
         redping_shown_time = 0

    # 움직이는 배경 화면 좌표값 변경 
    background_y  += background_speed
    background2_y += background_speed
    
    #ingame에서 운석 수 변경 
    if shotCount > 15 :
        rockfreq = 7
    
    if ( background_y == background_height ) :
        background_y =  -background_height
        
    if ( background2_y == background_height ) :
        background2_y = -background_height
      
    # 배경 화면 그리기        
    #drawObject( background, 0, 0 )  
    drawObject( background,  0, background_y )
    drawObject( background2, 0, background2_y )     

    
    # 운석 맞춘 점수 표시
    
    #게임시간이 3초가 되면 다음 화면으로 넘어감( 일단 중간에 왕 나오는 배경 3초씩 )
    gametime = int(time.time()-start_time)
    if gametime > 3 and gametime < 7 :
      background = pygame.image.load( './images/background0.png' )
      background2 = background.copy()
    if gametime > 6 and gametime < 10 :
      background = pygame.image.load( './images/background2.png' )
      background2 = background.copy()
    if gametime > 9 and gametime < 13 :
      background = pygame.image.load( './images/background0.png' )
      background2 = background.copy()
    if gametime > 12 and gametime < 16 :
      background = pygame.image.load( './images/background3.png' )
      background2 = background.copy()
      
   # 비행기를 게임 화면의 ( x, y ) 좌표에 그리기

    #운석 그리기


    for rockA, dy in rocks:
        rockA.top += dy * 0.7 #빠른 것 같아 느리게 
        rockA.left += dx * 0.7 # 운석 x축 움직임 추
        if rockA.top > padHeight:
            rocks.remove((rockA,dy)) # 운석 하나 떨어지면 게임오버 수정
            rockGen()
            rockPassed += 1
            
        if rockPassed == 5 : # 운석 3개 놓치면 게임오버
            gameOver( )
            
        for missileA in missileXY:
            missileA.top -= 5
            if missileA.top < 0:
                missileXY.remove(missileA)
            
    # 미사일 발사 화면에 그리기
    for rockA, dy in rocks:
        for missileA in missileXY:
            if missileA.colliderect(rockA):
                    missileXY.remove(missileA)
                    gamePad.blit(explosion, (rockA.left, rockA.top))
                    rocks.remove((rockA, dy))
                    rockGen()
                    shotCount += 1
                    destroySound.play()
                    
                    gaugeValue += 10
                    if gaugeValue > 100 :
                        gaugeValue = 100
  
    for rockA, dy in rocks:
        if rockA.colliderect(fighter.get_rect(left=mouse_x, top=mouse_y)):
            gameOver()


        
    for rockA, dy in rocks:
      gamePad.blit(rock, rockA)
    
      if rockA.left < 0 : # 운석 좌우 움직임 제한 
          dx = -dx
          #생성된 rock이 화면 오른쪽에 부딪힐 때
      elif rockA.left > ( padWidth - rock.get_width() ) :
          rockA.left = padWidth - rock.get_width() 
          dx = -dx
          
    for missileA in missileXY:
      gamePad.blit(missile, missileA)

    gamePad.blit(fighter, ( mouse_x-30, mouse_y))
    pygame.mouse.set_visible(0)
    #print(fighterA)
    
    destroySound = pygame.mixer.Sound( random.choice( explosionSound ) )
    writeScore( shotCount )
 
      
    # 놓친 운석 수 표시
    writePassed( rockPassed )
    
    # 핵미사일 그리기
    nuclears = [ [w[0], w[1] - nuclearMissile_speed] for w in nuclears ]
     
    for nuclearMissile_x_pos, nuclearMissile_y_pos in nuclears:
         screen.blit(nuclearMissile, (nuclearMissile_x_pos, nuclearMissile_y_pos))
     
    # 빨간점 그리기
    redpings = [ [w[0], w[1] - redping_speed] for w in redpings ]
     
    for redping_x_pos, redping_y_pos in redpings:
        screen.blit(redping, (redping_x_pos, redping_y_pos))

    
    #운석 그리기
    pygame.draw.rect(gamePad, (255, 0, 0), (gaugeX, gaugeY, gaugeWidth, gaugeHeight), border_radius=3)  # Red gauge bar
    
    pygame.draw.rect(gamePad, (0, 255, 0), (gaugeX, gaugeY, gaugeWidth * (gaugeValue / 100), gaugeHeight), border_radius=50)  # Green gauge bar based on gaugeValue
    useSkill(gaugeValue) 
    #pygame.display.update( )  # 게임 화면을 다시 그림
    
    # 보스를 게임 화면의 ( x, y ) 좌표에 그리기  
    if ( shotCount > 20 and shotCount < 40 ) :  
        shwoBoss( "mouse", 0, 1.0 )               
    elif ( shotCount > 60 and shotCount < 80 ) :   
        shwoBoss( "mouse", 1, 1.3 )  
    elif ( shotCount > 100  and shotCount < 120 ) :
        shwoBoss( "mouse", 2, 1.6 )
    elif ( shotCount > 140 )  :
        shwoBoss( "mouse", 3, 1.9 )
        
    pygame.display.update( )  # 게임 화면을 다시 그림
    
    
    gamePad.fill( BLACK )  # 게임 화면 ( 검은색 )
    
    clock.tick( 60 )  # 게임화면의 초당 프레임수를 60으로 설정
    
  pygame.quit( )  # pygame 종료
  
def rockGen ( ) :
    #rock = pygame.image.load( random.choice( rockImage ) ) 
    rockA = rock.get_rect(left=random.randint(0, padWidth - rock.get_width()), top=-30)
    dy = random.randint(1, 2)
    #print(rockA)
    rocks.append((rockA, dy))
  
# 전투기가 운석과 충돌했을 때 메시지 출력
def crash( ) :
    global shotCount
    
    writeMessage( '전투기 파괴!' )
    
# 게임 오버 메시지 출력
def gameOver( ) :
    global shotCount
    
    writeMessage( '게임 오버!' )
    
# 게임 메시지 출력
def writeMessage( text ) :
    global padWidth, padHeight, shotCount        
    
    textfont = pygame.font.Font( './images/NanumGothic.ttf', 40 )
    text = textfont.render( text, True, ( 255, 255, 255 ) )
    textpos = text.get_rect( )
    textpos.center = ( padWidth / 2, padHeight / 2 )
    gamePad.blit( text, textpos )
    pygame.display.update( )
    pygame.mixer.music.stop()  # 배경 음악 정지
    gameOverSound.play()  # 게임 오버 사운드 재생
    sleep( 2 )
    pygame.mixer.music.play( -1 )
    
    #종료시 게임순위 및 등록( 0점 이상일 때)
    if shotCount > 0 :
      game = Ranking() 
      game.showRanking( shotCount )
    
    initGame( )
    runMenu( )
  
"""* 기능구현부 *"""

# 게임에 등장하는 객체를 드로잉
def drawObject( obj, xx, yy ) :
  gamePad.blit( obj, ( xx, yy ) )
  
#스킬게이지 표시
def useSkill ( getgaugeValue ) :
  font = pygame.font.Font( './images/NanumGothic.ttf', 20 )
  text = font.render('skill gauge' , True ,(255, 255, 255) )
  gamePad.blit( text, (190,590))
  
# 운석을 맞춘 개수 표시
def writeScore( count ) :  
  font = pygame.font.Font( './images/NanumGothic.ttf', 20 )
  text = font.render( '파괴한 운석 수 :' + str( count ), True, ( 255, 255, 255 ) )
  gamePad.blit( text, (10, 0 ) )
  
# 운석이 화면 아래로 통과한 개수 표시
def writePassed( count ) :
  font = pygame.font.Font( './images/NanumGothic.ttf', 20 )
  text = font.render( '놓친 운석 :' + str( count ), True, ( 255, 255, 255 ) )
  gamePad.blit( text, (360, 0 ) )

# 보스을 화면에 표시
def shwoBoss( keyboardOrMouse, bossImage, bossSpeed ) :
    global boss, bossA, bossX, bossY
    global mouse_x, mouse_y, tmpX, tmpY
    
    bossX += tmpX * bossSpeed
    bossY += tmpY * bossSpeed

    if bossX < 0 :
        bossX = 0
        tmpX = -tmpX 
    elif bossX > ( padWidth - boss[ bossImage ].get_width() ) :
        bossX = padWidth - boss[ bossImage ].get_width() 
        tmpX = -tmpX           
      
    if bossY < 0 :
        bossY = 0
        tmpY = -tmpY 
    elif bossY > ( padHeight - boss[ bossImage ].get_height() ) :
        bossY =  padHeight - boss[ bossImage ].get_height()
  
        tmpY = -tmpY    
        tmpX = -tmpX            
    
    bossA = boss[ bossImage ].get_rect(left=bossX, top=bossY ) 
    drawObject( boss[ bossImage ], bossX, bossY )       
    
    if ( keyboardOrMouse == "keyboard" ) :
        if ( bossA.colliderect( fighter.get_rect(left=x,top=y ) ) ) :
            gameOver()
    else : #  mouse 
        if ( bossA.colliderect( fighter.get_rect( left=mouse_x, top=mouse_y ) ) ) :
            gameOver()  
          
# 랭킹시스템 구현
class GameRanking :
    
    #클래스 멤버 변수 정의
    conn = None
    curs = None 
    
    def __init__( self ) : 
        self.conn = None
        self.curs = None            
                     
    def __del__( self ) :                  
        del self      
        print( "class GameRanking 메모리 해제." )  
        
    
    # 게임점수가 랭킹에 포함되는지 체크
    def checkRanking( self, gameScore ) :
        
        rankingCnt = 5  # 보여줄 랭킹 갯수
        minScore = 0    # 랭킹순위중 가장 낮은 점수
        
        # MySQL Connection 연결
        self.conn = pymysql.connect( host='localhost', user='root', password='1234', db='pythonDB', charset='utf8' )    
         
        # Connection 으로부터 Dictoionary Cursor 생성
        self.curs = self.conn.cursor( pymysql.cursors.DictCursor )
         
        # SQL문 실행
        sql =  " Select IFNULL( MIN( a.gr_score ), 0 ) min_score, count(*) cnt From "
        sql += " (Select  gr_score  from gamerankingtbl order by gr_score desc LIMIT %s, %s ) a "           
                   
        self.curs.execute( sql, ( 0, rankingCnt ) )    # Top 5만 출력  
         
        # 데이타 Fetch
        rows = self.curs.fetchall()
        for row in rows:           
            minScore = row['min_score'] #    minScore 테이블 셀렉트한 첫번째 값 row[0] 입력
            cntRecord = row['cnt']      #    cntRecord 에 랭킹 총갯수
       
        # Connection 닫기
        self.conn.close()
     
        if ( gameScore > minScore or cntRecord < rankingCnt ) :    
            return True
        else:
            return False  
        
    ## 함수 선언부
    def addRankingToListBox( self ) :                      
        
        strData1, strData2, strData3, strData4  = [], [], [], []
        ranking = 1
        
        # MySQL Connection 연결
        self.conn = pymysql.connect(host='localhost', user='root', password='1234', db='pythonDB', charset='utf8')    
         
        # Connection 으로부터 Dictoionary Cursor 생성
        self.curs = self.conn.cursor( pymysql.cursors.DictCursor )
         
        # SQL문 실행            
        sql = " select gr_name, gr_score, gr_date from gamerankingtbl order by gr_score desc Limit %s, %s ;"
        self.curs.execute(sql, (0, 5))   # Top 5만 출력        
      
        # 데이타 Fetch
        rows = self.curs.fetchall()
        for row in rows:
         #   print(row)
            # 출력 : {'category': 1, 'id': 1, 'region': '서울', 'name': '김정수'}
            # print( row['gr_name'], row['gr_score'], row['gr_date'])
        
            strData1.append( ranking )
            strData2.append(row['gr_name']) # 리스트 strData1에 테이블 셀렉트한 첫번째 값 row[0] 입력
            strData3.append(row['gr_score'])
            strData4.append(row['gr_date'])
            ranking += 1
        
        # Connection 닫기
        self.conn.close()          
        
        return  strData1, strData2, strData3, strData4     
      
    # 랭킹순위 가져오기 
    def getRanking( self, gameScore ) :
        import pymysql     
      
        myRanking = 0    # 자신의 랭킹순위
        
        # MySQL Connection 연결
        self.conn = pymysql.connect( host='localhost', user='root', password='1234', db='pythonDB', charset='utf8' )    
         
        # Connection 으로부터 Dictoionary Cursor 생성
        self.curs = self.conn.cursor( pymysql.cursors.DictCursor )
         
        # SQL문 실행
        sql =  " SELECT count(gr_score) ranking FROM GameRankingtbl WHERE gr_score >= %s ORDER BY gr_score desc "           
                   
        self.curs.execute( sql, ( gameScore ) )    # 게임점수로 순위를 가져오게 쿼리 생성  
         
        # 데이타 Fetch
        rows = self.curs.fetchall()
        for row in rows:           
            myRanking = row['ranking']              
       
        # Connection 닫기
        self.conn.close()
     
        if ( myRanking == 0 ) :    # 0순위이면 1순위로 변경
            myRanking = 1            
        
        return myRanking  
    
      
class Ranking( GameRanking ) :   
    
    window = None
     
    def __init__( self ) : 
         super().__init__()    
         window = None
   
    def __del__( self ) :                   
        del self      
        print( "sub class ShowRanking 메모리 해제."  )            

    # 저장 버튼 클릭시 호출되는 함수
    def insertRanking( self, gameScore, name ) :       
        
        data1, data2  = "", 0
        sql = ""   
        insertOk = False           # 랭킹점수 저장후 재입력되는 것 방지 v0.3c
        
        # entry(한줄텍스트박스)로 입력받은 값을 data 변수들에 입력       
        if ( gameScore > 0 ) :
             data2 = gameScore      # 점수           
        else:
             return
        
        # MySQL Connection 연결
        self.conn = pymysql.connect(host='localhost', user='root', password='1234', db='pythonDB', charset='utf8')    
         
        # Connection 으로부터 Dictoionary Cursor 생성
        self.curs = self.conn.cursor(pymysql.cursors.DictCursor)     
        
        sql = "insert into gamerankingtbl(gr_name,gr_score) values('" + name + "'," + str(data2) + ");"
        
        try :   # 예외처리 시작      
            
            self.curs.execute( sql ) 
        except :    # 에러발생 시 작동
            messagebox.showerror('오류', '데이터 입력 오류가 발생함')
            insertOk = False
        else :  # 에러 없을 시 작동
            messagebox.showinfo('성공', '데이터 입력 성공')
            insertOk = True    
           
        self.conn.commit()
        self.conn.close()
       
        # 추가한 리스트 새로고침       
        self.window.destroy()
        self.showRanking( gameScore, insertOk )         # 랭킹점수 재입력 방지 v0.3c insertOk 인자 추가
        self.window.update()           
    
    # 랭킹점수 재입력 방지 v0.3c  ( 랭킹점수 추가후 insertFlag = True )
    def showRanking( self, gameScore, insertFlag = False ) :        
        
        ## 메인 코드부  
        BLACK = "#000000"
        WHITE = "#FFFFFF"
        GRAY  = "#CCCCCC" 
        RED   = "#FF0000"
        
        #창을 화면 중앙에 배치
        self.window = Tk()
        
        win_x = 370   # 창 넓이
        win_y = 194   # 창 높이
        tot_x = self.window.winfo_screenwidth()
        tot_y = self.window.winfo_screenheight()
        x_pos = int( ( tot_x / 2 ) - ( win_x / 2 ) )
        y_pos = int( ( tot_y / 2 ) - ( win_y / 2 ) )
        
        self.window.geometry(f"{win_x}x{win_y}+{x_pos}+{y_pos}")
    
        self.window.resizable(width=False, height=False) # 창 크기 고정
        self.window.title("슈팅게임 순위 V0.3b")
        
        # 게임 점수가 Top 5에 속할 경우 등록 버튼 나오게 처리      
        padding_top = Label( self.window, text = "", width = 1, height = 1 )
        padding_top.grid( row = 0, column = 0, columnspan = 6 )        
               
        if ( self.checkRanking( gameScore ) and insertFlag == False ) :             
            padding_left = Label( self.window, text = "", width = 1 )
            padding_left.grid( row = 1, column = 0 ) 
            
            label =Label( self.window, text = "이름입력", fg=BLACK, bg = WHITE )
            label.grid( row = 1, column = 1 ) 
            
            edt1 = Entry( self.window, width = 17, bg = WHITE, justify = LEFT )  
            edt1.grid( row = 1, column = 2 )
            
            btnInsert = Button( self.window, text="저 장", bg = WHITE, justify = LEFT
                               ,command = lambda  : self.insertRanking ( gameScore, edt1.get() ) )                      
            btnInsert.grid( row = 1, column = 3 )
            
            msg = "게임점수는 " +str( gameScore )+"점"
            label =Label( self.window, text = msg , fg=RED, bg = WHITE )
            label.grid( row = 1, column = 4 ) 
        else :
            msg =  "당신의 게임 순위는 [ " + str( self.getRanking( gameScore ) ) + "위 ], "
            msg += "점수는 [ " + str( gameScore ) + "점 ] 입니다."
            label =Label( self.window, text = msg , fg=RED, bg = WHITE )
            label.grid( row = 1, column = 0, columnspan = 5 ) 
            
        
        padding_top = Label( self.window, text = "", width = 1, height = 1 )
        padding_top.grid( row = 2, column = 0, columnspan = 6 ) 
        
        padding_left = Label( self.window, text = "", width = 1 )
        padding_left.grid( row = 3, column = 0 ) 
        
        label =Label( self.window, text = "순위", width = 7, fg=WHITE, bg = BLACK, justify = LEFT )
        label.grid( row = 3, column = 1 ) 
        
        label =Label( self.window, text = "사용자 이름", width = 16, fg=WHITE, bg = BLACK, justify = LEFT )
        label.grid( row = 3, column = 2 ) 
        
        label =Label( self.window, text = "점 수", width = 5, fg=WHITE, bg = BLACK, justify = LEFT )
        label.grid( row = 3, column = 3 ) 
        
        label =Label( self.window, text = "날 짜", width = 16, fg=WHITE, bg = BLACK, justify = LEFT )
        label.grid( row = 3, column = 4 )
        
        padding_left = Label( self.window, text = "", width = 1 )
        padding_left.grid( row = 4, column = 0 ) 
        
        listData1 = Listbox( self.window, bg = 'white', width = 7, height = 5, justify = LEFT )
        listData1.grid( row = 4, column = 1 )
         
        listData2 = Listbox( self.window, bg = 'white', width = 17, height = 5, justify = LEFT )
        listData2.grid( row = 4, column = 2 )
        
        listData3 = Listbox( self.window, bg = 'white', width = 5, height = 5, justify = LEFT )
        listData3.grid( row = 4, column = 3 )
        
        listData4 = Listbox( self.window, bg = 'white', width = 17, height = 5, justify = LEFT )
        listData4.grid( row = 4, column = 4 )
        
        padding_right = Label( self.window, text = "", width = 1 )
        padding_right.grid( row = 4, column = 5 )      
        
        listData1.delete( 0, listData1.size() - 1 )    # 리스트박스에 있는 값들을 모두 지워버림
        listData2.delete( 0, listData2.size() - 1 )
        listData3.delete( 0, listData3.size() - 1 )    # 리스트박스에 있는 값들을 모두 지워버림
        listData4.delete( 0, listData4.size() - 1 )                      
                
        strDate1, strDate2, strData3, strDate4 = ( self.addRankingToListBox() )
      
        for item1, item2, item3, item4 in zip( strDate1, strDate2, strData3, strDate4 ): #item에 strData들을 한줄씩 입력
            listData1.insert(END, item1)    # 리스트박스 마지막줄에 item 값들을 넣어줘서 보여
            listData2.insert(END, item2) 
            listData3.insert(END, item3)  
            listData4.insert(END, item4)              
            
        self.window.mainloop()    

""" * 실행부 * """
initGame( )
runStory( )
runGame( )