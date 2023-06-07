# Reinforcement Learning : Q-learning

<img src="demo/2.PNG" width="100%" height="400">
<img src="demo/1.png" width="100%" height="400" />

## Table of Contents

- [Qlearning Algorithm](#qlearning-algorithm)
  - [Introduction](#introduction)
  - [Environment](#environment)
  - [Q-learning](#q-learning)

## Introduction

Q-learning is a value-based reinforcement learning algorithm. The goal of Q-learning is to learn a policy, which tells an agent what action to take under what circumstances. It does not require a model of the environment and can handle problems with stochastic transitions and rewards, without requiring adaptations.

Q-learning can identify an optimal action-selection policy for any given finite Markov decision process (FMDP). It works by learning an action-value function that ultimately gives the expected utility of taking a given action in a given state and following the optimal policy thereafter.

A Q-learning agent interacts with the environment repeatedly. In each time step, the agent chooses an action, and the environment returns an observation and a reward. The goal is to maximize the total reward.

In this project, we will use Q-learning to train a taxi to pick up and drop off passengers at the right location.

We will use two different approaches to solve this problem. In the first approach, we will use a sequential way to solve the problem. In the second approach, we will use a multi-agent approach to solve the problem.

## Environment

The environment is a 5x5 grid world. There are 4 locations marked as R, G, Y, B. The taxi starts at a random square and the passenger is at a random location. The taxi has to pick up the passenger and drop it off at the right location. The taxi can move in 4 directions: south, north, east, west. The taxi cannot go outside the grid. The passenger can be picked up and dropped off at any of the 4 locations. The taxi gets a reward of -1 for each time step. The taxi gets a reward of +20 for a successful dropoff and -10 for a failed dropoff.

## Q-learning
