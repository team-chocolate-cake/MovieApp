package com.chocolatecake.viewmodel.tv_details

import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.common.listener.PeopleListener
import com.chocolatecake.viewmodel.tv_details.RateListener

interface TvDetailsListeners: RateListener, PeopleListener, MediaListener,RecommendedListener
{

}